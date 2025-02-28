demo-blaze-tests/

│── src/

│   ├── main/

│   │   ├── java/

│   │   │   ├── pages/          # Page Object Model classes

│   │   │   ├── utils/          # Utility classes (WebDriver, etc.)

│   ├── test/

│   │   ├── java/

│   │   │   ├── steps/           # Step Definitions for Cucumber

│   │   │   ├── services/        # JAVA methods for steps execution and result assertion

│   │   │   ├── tools/           # JAVA methods for table transformers

│   │   ├── resources 

│   │   │   ├── features/        # Gerkhin Cucumber .feature files

│   │   │   ├── test_files/      # json files with test data (e.g. usernames and passwords, payment credentials)

│   │   │   ├── test.yml         # yml configuration files

│   │   │   ├── logback-test.xml # log definitions

│── pom.xml                      # Maven dependencies

│── README.md
