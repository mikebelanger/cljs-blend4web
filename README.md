# cljsjs/blend4web

[](dependency)
```clojure
[cljsjs/blend4web "16.09-0"] ;; latest release
```
[](/dependency)

Note the version number will now reflect the blend4web version its compatible with.

### Clojurescript -> Blend4Web Web-player functions

Experiment to see if I can control [Blend4Web](http://www.blend4web.org/)'s outputted JS stuff with Clojurescript.

### Proposed workflow

You'd make a scene in [Blender](http://www.blender.org/) the usual way, use Blend4Web's exporter (JSON option) to make the un-manipulated 3d scene.  You would then load a scene with the exported files, only with Clojurescript instead of JS.  Here's what it would look like:
```clojure
(ns cube-test.core
  (:require blend4web))

(defn ^:export start
  [json]
  (let [m-main    (.require js/b4w "main")
        m-data    (.require js/b4w "data")
        m-scene   (.require js/b4w "scenes")
        m-config  (.require js/b4w "config")
        a-trans   (.require js/b4w "transform")
        m-camera  (.require js/b4w "camera")
        canvas    (.getElementById js/document "container")]

    (defn loaded-cb [data-id success]
      (.log js/console "Main scene loaded on thread number " data-id
      "with a success value of " success))

    (defn stageload-cb [data-id success]
      "Use camera, translation and probably other b4w modules within this
      function's scope."
      (when (.is-primary-loaded m-data)
                (let [current-camera (.get-active-camera m-scene)
                      cube           (.get-object-by-name m-scene "Cube")]

                      (.translate-view m-camera current-camera 1 1 -10))))

    (.set m-config "console_verbose" true)
    (.load m-data (str json) loaded-cb stageload-cb true false)
    (.init m-main canvas)))
```

```html
<!DOCTYPE html>
<html>
<head>
  <script src="main.js"></script>
</head>
<body onload="cube_test.core.start('cube_test.json');"</script>
  <canvas id="container" width="500" height="500"></canvas>
</body>
</html>
```

After this, you'd be able to control the scene by calling b4w's usual module functions.

### ...Why?

Blend4Web can output to a fairly performant WebGL-based scene, and its easily manipulated via the library's various own function calls. This means a more functional language like [Clojurescript](http://www.clojurescript.org/) could easily translate into b4w function calls.

It also turns out that Blend4Web, like Clojurescript, uses [Google Closure](https://developers.google.com/closure/), and already has its own extern files.  Surprisingly, b4w's externs appear to be enough for generating a functioning [cljsjs](http://cljsjs.github.io/) package.


### Why bother?  There's already xyz.

My main goal is getting 3d graphics into a beat-driven (aka audio-reactive) context.  While there's already some takes on this idea, I believe this setup will be the most optimal.

- I've been using Blender for 10 years, and, despite it's eccentricities, is the devil I know.
- I'm somewhat comfortable debugging JS, the language of blend4web's outputted webgl.
- While I've only been using Clojurescript for about 4 months, it's lispyness make it conducive to live-coding, as evidenced by tools like [Overtone](http://overtone.github.io/), [Afterglow](https://github.com/brunchboy/afterglow) and [Quil](http://www.quil.info/).  The language itself is also very expressive, and coerces me into better coding practices.

There's other software for this, but they'd force me to either learn a new 3d package, deal with 3d file format export issues, wrestle with JS, or all of the above.  I could just set the bar lower, and make triangles and circles, but that's just boring!

### What about outputting Clojurescript-driven stuff back into Blender?

It would be really cool if the live-coded scene be translated back into a Blender scene. Unfortunately, I can't figure this out, and I'm planning on Blender just outputting stuff.  If you have any ideas, please let me know!

### I've just skimmed through to download this and play.

I don't blame you, eager and hypothetical internetian.  Also, I'm glad you're interested.  Presuming you understand a bit about clojurescript, blend4web and JS, you're probably off to a good start.  If not, I have to warn you: this is unstable, and subject to change.  I can barely get this thing to work, so please be sure that you're comfortable with Clojurescript, boot-clj, JS, and even some blend4web.

Assuming you already have Java 7 or 8 running, Clojure, [boot-clj](http://www.boot-clj.com/), your best bet is to clone the package repo into your local maven repo.  On OS X/Linux, that's a command-lining of:

```bash
cd ~/.m2/cljsjs
git clone https://github.com/mikebelanger/blend4web.git
```
**Note that if it complains the cljsjs directory doesn't exist, just make an empty one.  It the package likes to live right there.

You then want to clone the [test repo](https://github.com/mikebelanger/blend4web_test), or...

```bash
cd ~/Downloads
git clone https://github.com/mikebelanger/blend4web_test.git
cd ~/Downloads/blend4web_test
boot build
```

Wait a bit while it builds everything, it'll finish when it tells you the elapsed time to build.  Then....

```bash
open ~/Downloads/blend4web_test/target/index.html
```

You should see your browser load the compiled HTML.  For now, nothing renders, but open up the console and see if you can figure it out!  I can't, and there's not that many blend4web users, so I could use a few pointers.

Windows users: If you know how to get this running on your systems, please let me know, and I'll add them here.
