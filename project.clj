(defproject usersearch.v1/v1 "1.0.0SNAPSHOT"
  :source-paths ["src" "test"]
  :resource-paths ["resources/"]
  :target-path "target/%s/"
  :clean-target [:target-path
                 (str :resource-paths "public/js")
                 ".shadow-cljs"]
  :dependencies [[thheller/shadow-cljs "2.16.12"]]
  :auto-clean true
  ;;:main ^:skip-aot
  :profiles {:ui {;;:source-paths ["src/ui"]
                  :dependencies [[reagent "1.1.0"]
                                 [re-frame "1.3.0-rc2"]]}}
  :repositories [["clojars" {:url "https://repo.clojars.org/"}]])