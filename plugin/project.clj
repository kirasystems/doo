(defproject kirasystems/lein-doo "0.1.8"
  :description "lein-doo is a plugin to run clj.test on different js environments."
  :url "https://github.com/kirasystems/doo"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :scm {:name "git"
        :url "https://github.com/kirasystems/doo"}

  :deploy-repositories [["clojars" {:url "https://clojars.org/repo"
                                    :sign-releases false
                                    :username :env
                                    :password :env}]]

  :eval-in-leiningen true

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [doo "0.1.7"]]

  :test-paths ["test/clj" "test/cljs"]

  :clean-targets ^{:protect false} [:target-path "resources/public/js/" "out"]

  :doo {:build "test"
        :alias {:default [:chrome]
                :browsers [:chrome :firefox]
                :dom [:browsers :headless]}}

  :profiles {:dev {:dependencies [[org.clojure/clojurescript "0.0-3308"
                                   :scope "provided"]]}
             ;;; To test managed dependencies, load a specific Clojure version
             :test {:dependencies [[org.clojure/clojure "1.8.0"
                                    :scope "test"]]}}

  :cljsbuild
  {:builds {:test {:source-paths ["test/cljs"]
                   :compiler {:output-to "resources/public/js/testable.js"
                              :main lein-doo.runner
                              :optimizations :whitespace}}}}

  ;;; Don't sign the tag, and don't deploy
  :release-tasks [["vcs" "assert-committed"]
                  ["change" "version" "leiningen.release/bump-version" "release"]
                  ["vcs" "commit"]
                  ["vcs" "tag" "--no-sign"]
                  ["change" "version" "leiningen.release/bump-version"]
                  ["vcs" "commit"]
                  ["vcs" "push"]])
