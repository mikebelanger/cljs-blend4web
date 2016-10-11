(def +lib-version+ "16.09")
(def +version+ (str +lib-version+ "-2"))

(set-env!
  :resource-paths #{"resources"}
  :dependencies '[[cljsjs/boot-cljsjs "0.5.2"]])

(require '[cljsjs.boot-cljsjs.packaging :refer :all]
         '[boot.task.built-in :refer :all])

(task-options!
 pom  {:project     'cljsjs/blend4web
       :version     +version+
       :description "Blend4Web -- Javascript WebGL Framework by Triump LLC"
       :url         "http://www.blend4web.org/"
       :license     {"GPL3" "https://github.com/TriumphLLC/Blend4Web/blob/master/license/GPL-license.txt"}
:scm {:url "https://github.com/cljsjs/packages"}})

(deftask uranium
  "Ensure uranium.js and uranium.js.mem are in target directory.  This is not
  needed to build the package, rather its for any projects using the package."
  []
  ;;uranium.js and uranium.js.mem need to both be in the same subdir as b4w's target html.
  (sift :add-jar {'cljsjs/blend4web #"^*/uranium.js*"}
        :move {#"^*cljsjs/blend4web/common/uranium.js"
               "uranium.js"}
        :to-resource #{#"uranium.js*"}))

(deftask package []
  (comp
   (download  :url      "https://raw.githubusercontent.com/TriumphLLC/Blend4Web/master/src/b4w.js"
              :checksum "e630f3cd36c4c0148cdfd11f16e05b08")
   (download  :url      "https://raw.githubusercontent.com/TriumphLLC/Blend4Web/master/deploy/apps/website/b4w.min.js"
              :checksum "E37949C81CE382FDC601749546AA7541")
   (download  :url      "https://raw.githubusercontent.com/TriumphLLC/Blend4Web/master/deploy/apps/common/b4w.whitespace.min.js"
              :checksum "ca2a992e3ab0e89106a08845afb13070")
   (download  :url      "https://raw.githubusercontent.com/TriumphLLC/Blend4Web/master/deploy/apps/common/b4w.simple.min.js"
              :checksum "36538a14ea487f4922cc8afadfdc5401")
   (download  :url      "https://raw.githubusercontent.com/TriumphLLC/Blend4Web/master/tools/closure-compiler/extern_fullscreen.js"
              :checksum "fe0a688b40a2f0c672e779f0aaced2a8")
   (download  :url      "https://raw.githubusercontent.com/TriumphLLC/Blend4Web/master/tools/closure-compiler/extern_modules.js"
              :checksum "9cd667bcb91b219429d604c938f7796c")
   (download  :url      "https://raw.githubusercontent.com/TriumphLLC/Blend4Web/master/tools/closure-compiler/extern_gl-matrix.js"
              :checksum "6a59beeb17a53e793ffe982d5be20ab0")
   (download  :url      "https://raw.githubusercontent.com/TriumphLLC/Blend4Web/master/tools/closure-compiler/extern_jquery-1.9.js"
              :checksum "33aa6a7c3c07031da56881f6eb8e433c")
   (download  :url      "https://raw.githubusercontent.com/TriumphLLC/Blend4Web/master/tools/closure-compiler/extern_pointerlock.js"
              :checksum "960a195b4ff3739ddc9047b42ce98a93")
   (download  :url      "https://raw.githubusercontent.com/TriumphLLC/Blend4Web/master/tools/closure-compiler/w3c_audio.js"
              :checksum "4a109980ba9fa64f564365ac4a641b0d")
   (download  :url      "https://raw.githubusercontent.com/TriumphLLC/Blend4Web/master/tools/closure-compiler/w3c_dom1.js"
              :checksum "745cfc581e8e901578f25d31a7442fdc")
   (download  :url      "https://raw.githubusercontent.com/TriumphLLC/Blend4Web/master/tools/closure-compiler/w3c_pointer_events.js"
              :checksum "56267aaf833d54dc5f514ba9d41d80f8")
   (download  :url      "https://raw.githubusercontent.com/TriumphLLC/Blend4Web/master/deploy/apps/common/uranium.js"
              :checksum "28b535d374af470b2526b1ced44e39a0")
   (download  :url      "https://raw.githubusercontent.com/TriumphLLC/Blend4Web/master/deploy/apps/common/uranium.js.mem"
              :checksum "3ff638d920819952240a1fdc28e6fd16")
   (sift      :move     {#"^b4w.js"
                         "cljsjs/blend4web/development/b4w.inc.js"
                         #"^b4w.min.js"
                         "cljsjs/blend4web/production/b4w.min.inc.js"
                         #"^b4w.whitespace.min.js"
                         "cljsjs/blend4web/development/b4w.whitespace.min.inc.js"
                         #"^b4w.simple.min.js"
                         "cljsjs/blend4web/development/b4w.simple.min.inc.js"
                         #"^extern_fullscreen.js"
                         "cljsjs/blend4web/common/extern_fullscreen.ext.js"
                         #"^extern_modules.js"
                         "cljsjs/blend4web/common/extern_modules.ext.js"
                         #"^extern_gl-matrix.js"
                         "cljsjs/blend4web/common/extern_gl-matrix.ext.js"
                         #"^extern_jquery-1.9.js"
                         "cljsjs/blend4web/common/extern_jquery-1.9.ext.js"
                         #"^extern_pointerlock.js"
                         "cljsjs/blend4web/common/extern_pointerlock.ext.js"
                         #"^w3c_audio.js"
                         "cljsjs/blend4web/common/w3c_audio.ext.js"
                         #"^w3c_dom1.js"
                         "cljsjs/blend4web/common/w3c_dom1.ext.js"
                         #"^w3c_pointer_events.js"
                         "cljsjs/blend4web/common/w3c_pointer_events.ext.js"
                         #"^uranium.js"
                         "cljsjs/blend4web/common/uranium.js"
                         #"^uranium.js.mem"
                         "cljsjs/blend4web/common/uranium.js.mem"})

   (sift      :include   #{#"^cljsjs"})
   (deps-cljs :name     "blend4web")
   (show)
   (pom)
   (jar)))
