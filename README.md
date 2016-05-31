# sonic-orders

This is a repo for a simple library jar project used for discussion with Sonic, America's Drive-In. A paper problem
discussing some development by an intern required refactoring and oversight by a more senior-level developer. This is 
my submittal. I wrapped the project with a Maven POM to allow easy building and testing of the subject code.

I also added several reporting plugins that assist with testing, code quality, and code style. The POM allows the creation of a real Maven site and that provides more insight into the intelligence of what we are trying to accomplish.

The Maven Project is actually hosted [here] (http://dlwhitehurst.com/sonic-challenge)

## Building, Testing, and Project Site Creation

I currently use Apache Maven version 3.3.9. This is enforced in the POM. You can modify or remove the prerequisites snippet but I'm not guaranteeing everything will work.

You can run:
<code>$ mvn test</code>
This will compile and run the JUnit tests.

If you run:
<code>$ mvn site:site</code>
This will build a full Maven project site at /target/site. Open a file explorer and double-click on index.html and you can find:

- JavaDoc (src/main/java and src/test/java)
- SrcDoc
- Cobertura Test Coverage
- SureFire Test Report
- Checkstyle Report
- PMD Coding Rules Report Validation
- Much more ...



David L. Whitehurst
