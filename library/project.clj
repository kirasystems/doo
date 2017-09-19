(defproject doo "0.1.7"
  :description "doo is a library to run clj.test on different js environments."
  :url "https://github.com/bensu/doo"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :scm {:name "git"
        :url "https://github.com/bensu/doo"}

  :repositories [["releases" {:url "https://clojars.org/repo"
                              :sign-releases false
                              :username :env
                              :password :env}]]

  :test-paths ["test/clj"]

  :resource-paths ["resources"]

  :clean-targets ^{:protect false} [:target-path "out"]

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/data.json "0.2.6"]
                 [karma-reporter "0.1.0"]]

  :jvm-opts ["-Xmx1g"]

  :profiles
  {:dev {:source-paths ["src/clj" "test/clj" "../example/src" "../example/test"]
         :dependencies [[org.clojure/core.async "0.1.346.0-17112a-alpha"]]}}

  ;;; Don't sign the tag, and don't deploy
  :release-tasks [["vcs" "assert-committed"]
                  ["change" "version" "leiningen.release/bump-version" "release"]
                  ["vcs" "commit"]
                  ["vcs" "tag" "--no-sign"]
                  ["change" "version" "leiningen.release/bump-version"]
                  ["vcs" "commit"]
                  ["vcs" "push"]])
