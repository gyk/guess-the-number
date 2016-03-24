(defproject guess-the-number "0.1.0-SNAPSHOT"
  :description "\"Guess the Number\" game solver"
  :url "http://github.com/gyk/guess-the-number"
  :license {:name "GNU General Public License"
            :url "http://www.gnu.org/licenses/gpl-3.0.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]]
  :source-paths ["src/clj"]
  :java-source-paths ["src/java"]
  :javac-options ["-target" "1.7" "-source" "1.7"])
