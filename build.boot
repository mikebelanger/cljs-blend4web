(def +lib-version+ "0.0.2")
(def +version+ (str +lib-version+ "-0"))

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

(deftask package []
  (comp
   (download  :url      "https://raw.githubusercontent.com/TriumphLLC/Blend4Web/master/src/b4w.js"
              :checksum "e630f3cd36c4c0148cdfd11f16e05b08")
   (download  :url      "https://raw.githubusercontent.com/TriumphLLC/Blend4Web/master/deploy/apps/website/b4w.min.js"
              :checksum "571680aacdec6a8673c94eae595c1a6d")
   (sift      :move     {#"^b4w.js"
                         "cljsjs/blend4web/development/b4w.inc.js"
                         #"^b4w.min.js"
                         "cljsjs/blend4web/production/b4w.min.inc.js"})
   (sift      :include  #{#"^cljsjs"})
   (deps-cljs :name     "blend4web")
   (show)
   (pom)
   (jar)))
