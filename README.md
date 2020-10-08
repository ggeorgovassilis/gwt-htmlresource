# gwthtmlresource

A HTML resource generator for GWT. License [APL 2](LICENSE)

## What

TL;DR: Works just like `TextResource` but minifies the inlined HTML.

## In detail

GWT resource bundles inline images and textual information into the generated JavaScript deployable. If you need HTML fragments (eg. because you abudantly use `HTMLPanel`s you would normally inject them with a `TextResource` such as:
```java

interface ScreenTemplates extends ClientBundle {

	@Source("install-app-prompt.html")
	TextResource appInstallPrompt();
  
  ...
  
  ```
  
 The example above would pick up `install-app-prompt.html`, inline it into the generated `.nocache.js` file(s) and return the html contents when `appInstallPrompt()` is invoked.
 
 If we substitute `TextResource` with `HtmlResource` then the example becomes: 
 ```java

interface ScreenTemplates extends ClientBundle {

	@Source("install-app-prompt.html")
	HtmlResource appInstallPrompt();
  
  ...
  
  ```
  but the returned HTML content will be minified.

# How to use

The artefact isn't available on maven central, so you have to add a repository:

```xml
<repositories>
	<repository>
		<id>gwt-sl-mvn-repo</id>
		<url>https://raw.github.com/ggeorgovassilis/gwt-htmlresource/mvn-repo/</url>
		<snapshots>
			<enabled>true</enabled>
			<updatePolicy>always</updatePolicy>
		</snapshots>
	</repository>
</repositories>
```
and then use the dependency

```
<dependency>
	<groupId>com.georgovassilis</groupId>
	<artifactId>gwt-htmlresource</artifactId>
	<version>0.0.1-SNAPSHOT</version>
</dependency>
```
