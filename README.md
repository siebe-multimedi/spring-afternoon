# Day 1 - Afternoon exercises

Nine afternoon exercises that go with the blocks of the day, numbered in the order of the blocks.
Hand them out per block, or use them as a block of practice later in the day.

| # | Package | Block | Format |
|---|---------|-------|--------|
| 1 | `cycle` | block 1 | red tests |
| 2 | `strategy` | block 2 | red tests |
| 3 | `lifecycle` | block 3 | run and observe |
| 4 | `lazy` | block 3 | red tests |
| 5 | `profiles` | block 4 | run and observe |
| 6 | `config` | block 5 | run and observe |
| 7 | `props` | block 5 | red tests |
| 8 | `tx` | block 6 | red tests |
| 9 | `autoconfig` | block 7 | red tests |

There are two formats:

- **Red tests.** The tests describe what the code must do. You change the code (not the tests)
  until they are green. A failing test at the start is the point of the exercise.
- **Run and observe.** You run `AfternoonApplication` and read what it prints. Every line starts with
  `[lifecycle]`, `[profiles]` or `[config]`, so you can find your own output in the console. The
  application starts, prints a few lines and stops again: that is expected, there is no web
  server.

Requires Java 17+ and Maven (or an IDE that bundles both).

## How to work

- **Work in pairs.** One person types, the other reads along and thinks ahead. Swap every ten
  minutes or so.
- **Run one test class at a time**, from your IDE or with
  `mvn test -Dtest=LazyInitializationTest` (use the class name of the exercise you are on). Running
  all tests at once shows many red tests at the start, by design.
- **Read the failure message before you change anything.** The last "Caused by" line is usually
  the one that matters.
- The tests are the specification. Only change a test where the exercise tells you to.
- Most exercises have a **bonus**. If you finish early, do the bonus before you move on.

## How to pass a profile or a property from outside the yaml file

The profiles exercise (5) needs this. Pick whatever works in your setup:

- **IDE:** add program arguments to the run configuration, for example
  `--spring.profiles.active=prod`.
- **Maven:** `mvn spring-boot:run "-Dspring-boot.run.arguments=--spring.profiles.active=prod"`
  (no packaging, and it does not run the tests).
- **Command line:** build once with `mvn package -DskipTests` (the starter's tests are red by
  design, so a plain `mvn package` fails), then
  `java -jar target/spring-day1-afternoon-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod`.
- **Environment variable:** `SPRING_PROFILES_ACTIVE=prod` (PowerShell:
  `$env:SPRING_PROFILES_ACTIVE="prod"`, cmd: `set SPRING_PROFILES_ACTIVE=prod`), then start the app.

---
## Exercise 1 (package `cycle`) - Circular dependencies (block 1)

Files: `OrderProcessor.java`, `InventoryChecker.java`, `CircularDependencyTest.java`

An `OrderProcessor` needs an `InventoryChecker` to know if there is enough stock. The
`InventoryChecker` needs the `OrderProcessor` to know what is already ordered.

1. Run `CircularDependencyTest` and read the failure. Find the line that tells you what the
   problem is. Which two beans are involved?
2. Do **not** use setter injection, `@Lazy` or `spring.main.allow-circular-references`. Those hide
   the problem. Redesign instead: what does each class actually need from the other? Extract
   that into a third class that both can depend on.
3. Add your new class to the `withUserConfiguration(...)` list in the test, and make all three
   tests pass.

**Bonus:** which of the three workarounds from step 2 would also have made the context start,
and what would be wrong with each? (To try `spring.main.allow-circular-references` in this test,
use `.withAllowCircularReferences(true)` on the runner: the property itself has no effect in an
`ApplicationContextRunner`.)

---

## Exercise 2 (package `strategy`) - Injecting all beans of a type (block 2)

Files: `PaymentGateway.java`, `CardGateway.java`, `SepaGateway.java`, `PaymentRouter.java`,
`Checkout.java`, `PaymentRouterTest.java`, `CheckoutTest.java`

Spring can inject **every** bean of a type at once: as a `Map` (the key is the bean name) or as a
`List` (sorted by `@Order`).

1. Implement `PaymentRouter.pay(...)`: pick the gateway by its **bean name** (look at the keys
   of the map, for example `sepaGateway`) and charge it. An unknown name must throw an
   `IllegalArgumentException`.
2. Make the test about the order of the gateways pass: SEPA first, then card. You only need
   one annotation on each gateway.
3. Run `CheckoutTest`. `Checkout` asks for a single `PaymentGateway`, and there are two. Read the
   message, then make the test pass: SEPA is the default.
4. Look at the name of the constructor parameter in `Checkout`. What would happen if you renamed
   it to `sepaGateway`? Try it, and decide whether you would rely on that in real code.

---

## Exercise 3 (package `lifecycle`) - Bean lifecycle (block 3)

Files: `LifecycleDemo.java`, `ReportHeader.java`, `ResourceHolder.java`, `LifecycleRunner.java`

1. **Predict, then run.** `LifecycleDemo` prints a line from its constructor, from
   `@PostConstruct` and from `@PreDestroy`. Before you start the application, write down the
   order you expect and whether `settings` is already injected at each point. Then run it and
   compare. When does the `@PreDestroy` line appear?
2. **A constructor that cannot see its dependencies.** In `ReportHeader.java`, uncomment
   `@Component`, start the application and read the error. Find the line in the stack trace that
   points at your own code, and explain why it fails. Then fix the class so it starts (the fix
   belongs in `@PostConstruct`).
3. **Release what you open.** `ResourceHolder` has an `open()` and a `close()` method, but
   nothing calls them. Add the two lifecycle annotations so that `open()` runs once right after
   the bean is wired, and `close()` runs on shutdown. Run again: do you see both lines?

**Bonus:** make `ResourceHolder` prototype-scoped (`@Scope("prototype")`) and run again. Look at
the `[lifecycle]` lines: how many times was the resource opened, is it ever closed, and what does
"same instance" say now? Why?

---

## Exercise 4 (package `lazy`) - Lazy initialization (block 3)

Files: `HeavyService.java`, `LazyInitializationTest.java`

`HeavyService` is expensive to create. A counter records how many instances were ever built, so
the test can see **when** Spring creates the bean.

1. Run `LazyInitializationTest`. One test fails. Which one, and what does the counter say?
2. Make the failing test pass by changing `HeavyService` only. You need one annotation.
3. Both tests are green now. Explain to your partner what Spring did differently at startup.

**Bonus:** add a bean `Report` whose constructor takes a `HeavyService`, register it in the
test, and look at the counter at startup. What happened? Now put `@Lazy` on the constructor
parameter of `Report` and look again. Why does that work?

---

## Exercise 5 (package `profiles`) - Application.yaml & profiles (block 4)

Files: `application.yaml` (the first section and the `---` sections), `ConsoleSender.java`,
`EmailSender.java`, `ProfilesRunner.java`

`application.yaml` has shared settings in the first section, and a `dev` and a `prod` section that
override the greeting. `ProfilesRunner` prints one `[profiles]` line.

1. Run the application as-is. Which profile is active, which sender is used, and where does the
   greeting come from?
2. Switch to `prod` **without editing the yaml file** (see "How to pass a profile" above). What
   changed in the `[profiles]` line? One value did not change: which one, and why?
3. Comment out the profile setting at the top of the yaml file and run again. Comment out **all
   three lines** (`spring:`, `profiles:` and `active: dev`): leaving the first two in place with
   nothing under them makes Spring Boot refuse to start. What do you see? Is there an error? What
   would you add to your own project so this cannot go unnoticed?
4. Set `dev` active again, and pass `--app.greeting="Hello from the command line"` as an
   additional argument. Which greeting wins: the one in the yaml `dev` section, or the one from the command
   line?

**Bonus:** in `ProfilesRunner`, inject `MessageSender` directly instead of through an
`ObjectProvider`, and repeat step 3. What happens now, and is that better or worse than step 3?

---

## Exercise 6 (package `config`) - @ConfigurationProperties (block 5)

Files: `MailProperties.java`, `MailRunner.java`, `application.yaml` (the `mail:` section),
`MailPropertiesTest.java`

1. Run the application and read the `[config]` line. `port: 587` is written as plain text in the
   yaml file, but `getPort()` returns an `int`: who did that conversion?
2. Add a `retries` field (with getter and setter) to `MailProperties`, add `retries: 3` to the
   `mail:` section in the yaml file, and extend the `[config]` line in `MailRunner` to print it.
3. Add a `Duration connectionTimeout` field to `MailProperties`, and add
   `connection-timeout: 5s` to the yaml file (with a hyphen, not camel case). Print it as well.
   Why does this bind to a Java field called `connectionTimeout`, and how does `5s` become a
   `Duration`?
4. **A silent typo.** Change `host:` to `hots:` in the yaml file and run again. Is there an error?
   Now add `@Validated` to `MailProperties` and `@NotBlank` to the `host` field, and run again.
   What happens, and how useful is the message? Put the key back when you are done. (While the typo
   is in place, `mvn test` fails too. That is expected.)

**Bonus:** set an environment variable `MAIL_HOST=other.example.org` and start the application
again. Which host is printed, the one in the yaml file or the one from the environment? Then
write a tiny bean of your own with a plain `@Value("${mail.host}")` field, put the `hots:` typo
back in the yaml file, and start the application: which approach complains about the typo
first, `@Value` or an unvalidated `@ConfigurationProperties` class?

---

## Exercise 7 (package `props`) - Records as @ConfigurationProperties (block 5)

Files: `SmtpSettings.java`, `SmtpSettingsTest.java`

`SmtpSettings` is a record bound from the `smtp.*` properties. Spring Boot binds records through
their constructor, no setters needed. Make the tests pass:

1. `retries` must default to `3` and `timeout` to `5s` when they are not set.
2. A missing `host` must prevent the application from starting.
3. A `port` outside 1 to 65535 must prevent the application from starting.

Hints: `@DefaultValue`, `@Validated`, and the constraints `@NotBlank`, `@Min` and `@Max`.

**Bonus:** add a second property `smtp.sender` (an email address) and make the code reject a
value that is not a valid email address. Which constraint annotation do you need?

---

## Exercise 8 (package `tx`) - @Transactional rules (block 6)

Files: `Payment.java`, `PaymentRepository.java`, `PaymentRejectedException.java`,
`PaymentService.java`, `PaymentServiceTest.java`

`PaymentService.pay(...)` saves a payment and then rejects amounts above 1000. A rejected
payment must not stay in the database.

1. Run `PaymentServiceTest`. Which test fails? How many rows are in the table, and why?
2. Look at the exception that is thrown. What kind of exception is it? Find out what Spring's
   **default** rollback rule is, and fix the service with a change to the annotation.
3. The first test checks that the injected service is a proxy. Why is it a proxy, when
   `PaymentService` has no interface?

**Bonus:** change `PaymentRejectedException` so that it extends `RuntimeException`, and restore
the original annotation. Does the test pass now? What did that prove?

---

## Exercise 9 (package `autoconfig`) - Write your own mini auto-configuration (block 7)

Files: `Notifier.java`, `ConsoleNotifier.java`, `NotifierAutoConfiguration.java`,
`NotifierAutoConfigurationTest.java`

This is the pattern Spring Boot uses for `DataSourceAutoConfiguration`, which you took apart
this morning. `NotifierAutoConfiguration` always creates a `ConsoleNotifier`. Make it behave
like a real auto-configuration:

1. It must **back off** when the user defines their own `Notifier` bean.
2. It must be possible to **switch it off** with the property `notifier.enabled=false`, and it
   must be **on by default** when the property is missing.

Hints: look at `@ConditionalOnMissingBean` and `@ConditionalOnProperty` (and its
`matchIfMissing` attribute). Run the tests after each change and read which one still fails.

**Bonus:** make Spring Boot actually load your class as an auto-configuration. Create the file
`src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`
containing the full class name of `NotifierAutoConfiguration` on one line. Then start
`AfternoonApplication` with the argument `--debug` and find your class in the conditions report.
Which section is it in, and which condition(s) does it list?
