(defproject functional-assignment "0.1.0-SNAPSHOT"
  :description "Functional Programming Assignment"
  :url "https://github.com/caseyhenderson/functional-assignment"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.clojure/tools.reader "1.1.3.1"]]
  :main ^:skip-aot functional-assignment.core
  :target-path "target/%s" 
  :test-paths ["test"]
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
