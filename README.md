# cljs-Blend4Web

[](dependency)
```clojure
[cljsjs/blend4web "16.09-0"] ;; latest release
```
[](/dependency)

*Note the version number will now reflect the blend4web version its compatible with.  Also note: is a an alpha-quality software, and is probably going to break/change.*

### Clojurescript -> Blend4Web Web-player functions

Controlling [Blend4Web](http://www.blend4web.org/)'s outputted WebGL with Clojurescript.  For an overview, check out the [proposed workflow](https://github.com/mikebelanger/blend4web/wiki/Clojurescript--Blend4Web).

[Here it is in action](https://mikebelanger.github.io/blend4web_test/target/)

*Note: Probably runs best on Chrome*

### Developing

#### 1: Downloading/Installing
* [Java](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) 7/8 (8 recommended)
* Latest version of [Clojure](http://www.clojure.org/)
* The [boot](http://www.boot-clj.com/) project building tool.
* [Git](http://www.git-scm.org/)

If you'd like to develop the 3d content as well, download the [latest version of Blender](http://www.blender.org/download) and the [blend4web-addon](https://www.blend4web.com/pub/blend4web_addon_16_09_2.zip)

This next step varies from OS to OS

#### 2: Building JAR (Linux/OS X Users)

First download this repo, and get into a terminal emulator (ie Terminal.app, iTerm) and enter:
```bash
git clone https://github.com/mikebelanger/clojure-blend4web.git
cd clojure-blend4web

```
Now compile this repo into a .jar, which sits in your ~/.m2 (maven repository).  This also downloads a bunch of code, so it may take some time.
```
boot package install
```

#### 2: Building JAR (Windows Users)
Not sure :P  I'm sure there's a way, but I don't have access to a Windows machine atm.  If you could tell me how to build the above .jar on Windows, please let me know, I'll put it on here.


#### 3: Next Steps

So far just a basic tutorial, I promise I'll add more as this project starts to stabilize.

[Starting a project from scratch](https://github.com/mikebelanger/cljs-blend4web/wiki/Starting-a-new-project)
