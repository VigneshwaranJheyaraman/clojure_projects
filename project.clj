(defproject usersearch.v1/v1 "1.0.0SNAPSHOT"
  :source-paths ["src" "test"] ;;have some folder like api inside that have the main api folder then the source-paths will be like src/api (i.e., src/api/api/main.clj)
  :resource-paths ["resources/"]
  :target-path "target/%s/"
  :clean-target [:target-path
                 (str :resource-paths "public/js")
                 ".shadow-cljs"]
  :dependencies [[thheller/shadow-cljs "2.16.12"]
                 [org.clojure/clojure "1.10.3"]
                 [metosin/reitit "0.5.15"]
                 [ring/ring-jetty-adapter "1.8.2"]
                 [cprop "0.1.19"]
                 [mount "0.1.16"]]
  :auto-clean true
  ;;:main ^:skip-aot
  :aliases {"cljs-repl" ["repl" ":connect" "localhost:9090"]}
  :repl-options {:init-ns api.main
                 :nrepl-middleware [shadow.cljs.devtools.server.nrepl/middleware]}
  :profiles {:ui {:source-paths ["src"] ;;same as api source-path
                  :dependencies [[reagent "1.1.0"]
                                 [re-frame "1.3.0-rc2"]]}}
  :repositories [["clojars" {:url "https://repo.clojars.org/"}]])