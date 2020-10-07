# gwthtmlresource

A HTML resource generator for GWT. 

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
