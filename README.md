# cljsjs/blend4web

[](dependency)
```clojure
[cljsjs/blend4web "0.0.0-1"] ;; latest release
```
[](/dependency)

### Clojurescript -> Blend4Web Web-player functions

Experiment to see if I can control [Blend4Web](http://www.blend4web.org/)'s outputted JS stuff with Clojurescript.  This may not make any sense.

### ...Why?

Blend4Web can output to a fairly performant JS, and it turns out that it's easily manipulated via the library's various function calls alone.  This makes it an interesting candidate to be controlled from Clojurescript.  Probably no partials are needed either, unlike more OO-based 3d JS frameworks.

It also turns out that Blend4Web, like Clojurescript, uses [Google Closure](https://developers.google.com/closure/), and already has a bunch of externs declared for the compilation process.  This means I might be able to use those same externs for Clojurescript compilation.  That said, because the externs are made for a different compilation process,  retrofitting b4w's externs may not make any sense.  I may end up wrapping the original code manually and namespace it into the final html file.  But that's a lot more work.  I'll try the (seemingly) easy way first.

### Why bother?  There's already plenty of 3d frameworks out there.

True.  This tool is very personal for me.  Blender is the devil I know, as well as Javascript, and Clojure is becoming my favourite live-coding language.  I'd ultimately like to live-code 3d stuff, with minimal exporting issues.  Because Blend4Web's whole thing is getting Blender into JS, I know there's going to be virtually no exporting issues.  Because Clojurescript is a lisp. along with it's Clojure-based libraries like Overtone, live-coding won't be too difficult.  There's other frameworks that do all of that, but I'd have to either learn a new 3d package, deal with exporter issues, or wrestle with a half-assed JS livecoding framework, or all of the above.

### What about outputting Clojurescript-driven stuff back into Blender?

It would be really cool if what happened in the browser could somehow be translated back into Blender, creating a sort of open-source trifecta of awesome.  Unfortunately, I can't think of how to do this, and I'm planning on Blender just outputting stuff.
