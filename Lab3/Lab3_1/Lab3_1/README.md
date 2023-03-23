
# Lab 3_1

**a) Identify a couple of examples that use AssertJ expressive methods chaining.**

```java
    assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName());

    assertThat(found).extracting(Employee::getName).containsOnly("bob");

    assertThat(response.getBody()).extracting(Employee::getName).containsExactly("bob", "alex");
```
**b) Identify an example in which you mock the behavior of the repository (and avoid involving a 
database).**

We mock the behavior of the repository using Mockito library. First, the user creates a mocked variable and injects the mock as shown below:

```java
@Mock( lenient = true)
    private EmployeeRepository employeeRepository;

@InjectMocks
    private EmployeeServiceImpl employeeService;
```

Then, user can use the mocked variable and control the mocked response with Mockito.when() method.
```java
        Employee john = new Employee("john", "john@deti.com");
        john.setId(111L);

        Employee bob = new Employee("bob", "bob@deti.com");
        Employee alex = new Employee("alex", "alex@deti.com");

        List<Employee> allEmployees = Arrays.asList(john, bob, alex);

        Mockito.when(employeeRepository.findByName(john.getName())).thenReturn(john);
        Mockito.when(employeeRepository.findByName(alex.getName())).thenReturn(alex);
        Mockito.when(employeeRepository.findByName("wrong_name")).thenReturn(null);
        Mockito.when(employeeRepository.findById(john.getId())).thenReturn(Optional.of(john));
        Mockito.when(employeeRepository.findAll()).thenReturn(allEmployees);
        Mockito.when(employeeRepository.findById(-99L)).thenReturn(Optional.empty());
```
**c) What is the difference between standard @Mock and @MockBean?**

- @Mock is an annotation provided by the Mockito library, which is a mocking framework for Java. It creates a mock object of a class or interface and is usually used in unit testing to isolate the code under test and test it in isolation from its dependencies. The scope of the mock object created by @Mock is limited to the test method in which it is used.

- @MockBean is an annotation provided by SpringBoot. It not only initializes the mock object but also adds them to the Spring application context. The mock will replace any existing bean of the same type in the app context.

**d) What is the role of the file “application-integrationtest.properties”? In which conditions will it be 
used?**

The "application-integrationtest.properties" file is used to provide configuration properties specific to the integration tests. For instance, in this project it is containing data about the database that is necesarry to perform the service and database integration test (like username, password, database address and method of testing the database).

**e) The sample project demonstrates three test strategies to assess an API (C, D and E) developed 
with SpringBoot. Which are the main/key differences?**

- Strategy C is a verification technique that focuses on testing the behavior of boundary components without involving the database.

- Strategy D is an integration testing technique that verifies the behavior of the boundary components with the full Spring Boot application loaded. This approach involves multiple components, including the REST endpoint, service implementation, repository, and database. 

- Strategy E is an integration testing technique that is similar to Strategy D but focuses specifically on testing the REST API using an explicit HTTP client. 