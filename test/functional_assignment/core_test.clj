(ns functional-assignment.core-test
  (:require [clojure.test :refer :all]
            [functional-assignment.core :refer[morse-to-ascii ascii-to-morse]]))

(deftest morse-to-ascii-test
  (testing "Morse to ASCII conversion, and ASCII to morse conversion"
    (is (= (morse-to-ascii ".... . .-.. .-.. ---  .-- --- .-. .-.. -..") "HELLO WORLD"))
    (is (=(ascii-to-morse "Hello World") ".... . .-.. .-.. ---  .-- --- .-. .-.. -.."))
    )
  ) 