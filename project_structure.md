demo-blaze-tests/
│── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── pages/          # Page Object Model classes
│   │   │   ├── utils/          # Utility classes (WebDriver, etc.)
│   ├── test/
│   │   ├── java/
│   │   │   ├── stepdefinitions/ # Step Definitions for Cucumber
│   │   │   ├── services/        # Запуск тестов
│   │   │   ├── features/        # Gerkhin Cucumber .feature files
│   │   ├── resources 
│   │   │   ├── test_files/      # json files with test data (e.g. usernames and passwords, payment credentials)
│   │   │   ├── test.yml         # yml configuration files
│   │   │   ├── logback-test.xml # log definitions
│── pom.xml                      # Maven dependencies
│── README.md
