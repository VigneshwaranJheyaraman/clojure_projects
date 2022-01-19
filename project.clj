(defproject usersearch.v1/v1 "1.0.0SNAPSHOT"
  :source-paths ["src" "test"]
  :resource-paths ["resources/"]
  :target-path "target/%s/"
  :clean-target [:target-path
                 (str :resource-paths "public/js")
                 ".shadow-cljs"]
  :dependencies [[thheller/shadow-cljs "2.16.12"]
                 [ring/ring-core "1.8.2"]
                 [ring/ring-jetty-adapter "1.8.2"]
                 [compojure "1.6.2"]]
  :auto-clean true
  ;;:main ^:skip-aot
  :aliases {"cljs-repl" ["repl" ":connect" "localhost:9090"]}
  :repl-options {:init-ns api.main
                 :nrepl-middleware [shadow.cljs.devtools.server.nrepl/middleware]}
  :profiles {:ui {;;:source-paths ["src/ui"]
                  :dependencies [[reagent "1.1.0"]
                                 [re-frame "1.3.0-rc2"]]}}
  :repositories [["clojars" {:url "https://repo.clojars.org/"}]])