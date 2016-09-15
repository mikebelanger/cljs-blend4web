# cljsjs/blend4web

[](dependency)
```clojure
[cljsjs/blend4web "0.0.2-0"] ;; latest release
```
[](/dependency)

### Clojurescript -> Blend4Web Web-player functions

Experiment to see if I can control [Blend4Web](http://www.blend4web.org/)'s outputted JS stuff with Clojurescript.  This may not make any sense.

### Ideal workflow

You'd make a scene in [Blender](http://www.blender.org/) the usual way, use Blend4Web's exporter (JSON option) to make the un-manipulated 3d scene.  You would then load a scene with the exported files, only with Clojurescript instead of JS.  Here's what it would look like:
```clojure
(ns cube-test.core
  (:require blend4web))

(defn ^:export start
  []
  (let [m-main  (.require js/b4w "main")
        m-data  (.require js/b4w "data")
        canvas  (.getElementById js/document "container")]

    (.init m-main canvas)
    (.load m-data "cube_test.json")))
```

```html
<!DOCTYPE html>
<html>
<head>
  <title>Basic Blend4Web->Clojurescript</title>
  <script src="main.js"></script>
</head>
<body>
  <canvas id="container"></canvas>
  <script>
    window.onload = cube_test.core.start();
  </script>
</body>
</html>
```

After this, you'd be able to control the scene by invoking b4w's usual function calls.

### ...Why?

Blend4Web can output to a fairly performant WebGL-based scene, and its easily manipulated via the library's various own function calls. This means a more functional language like [Clojurescript](http://www.clojurescript.org/) could easily translate into b4w function calls.

It also turns out that Blend4Web, like Clojurescript, uses [Google Closure](https://developers.google.com/closure/), and already has its own extern files.  Surprisingly, b4w's externs appear to be enough for generating a [cljsjs](http://cljsjs.github.io/) package.  Then again, that package can only seem to [*initialize* a b4w scene via Clojurescript](https://github.com/mikebelanger/blend4web_test). I can't actually trigger it's rendering yet.


### Why bother?  There's already xyz.

My main goal is getting 3d graphics into a beat-driven (aka audio-reactive) context.  While there's already some takes on this idea, I believe this setup will be the most optimal.

- I've been using Blender for 10 years, and, despite it's eccentricities, is the devil I know.
- I'm somewhat comfortable debugging JS, the language of blend4web.
- While I've only been using Clojurescript for about 3 months, it's lispyness make it conducive to live-coding, as evidenced by tools like [Overtone](http://overtone.github.io/), [Afterglow](https://github.com/brunchboy/afterglow) and [Quil](http://www.quil.info/).  The language itself is also very expressive, and coerces me into better coding practices.

There's other software for this, but they'd force me to either learn a new 3d package, deal with 3d file format export issues, wrestle with JS, or all of the above.  I could just set the bar lower, and make triangles and circles, but that's just boring!

### What about outputting Clojurescript-driven stuff back into Blender?

It would be really cool if the live-coded scene be translated back into a Blender scene. Unfortunately, I can't figure this out, and I'm planning on Blender just outputting stuff.  If you have any ideas, please let me know!

### I've just skimmed through everything so I can download this and play around.

I don't blame you, hypothetical internetian.  Also, I'm glad you're interested.  Presuming you understand a bit about clojurescript, blend4web and JS, you're probably off to a good start.  That said, I can barely get this thing to work, so please be sure that you're comfortable with Clojurescript, boot-clj, JS, and even some blend4web.

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
