(ns functional-assignment.core
  (:gen-class))
(require '[clojure.string :as str])
; define a map
; simple way of doing it - thank mr AI man
; need to get it in one function / map
; currently has to be upper case - fix this
; bit of AI inspiration - we can improve here
;; (defn asciiMorse [s]
;;   (let [morse-map {"A" ".-", "B" "-...", "C" "-.-.", "D" "-..", "E" ".",
;;                    "F" "..-.", "G" "--.", "H" "....", "I" "..", "J" ".---",
;;                    "K" "-.-", "L" ".-..", "M" "--", "N" "-.", "O" "---",
;;                    "P" ".--.", "Q" "--.-", "R" ".-.", "S" "...", "T" "-",
;;                    "U" "..-", "V" "...-", "W" ".--", "X" "-..-", "Y" "-.--",
;;                    "Z" "--.."}]
;;     (clojure.string/join " " (map morse-map (str/split s #"")))))
;; (def morse-code-map {"A" ".-", "B" "-...", "C" "-.-.", "D" "-..", "E" ".", "F" "..-.", "G" "--.", "H" "....", "I" "..", "J" ".---", "K" "-.-", "L" ".-..", "M" "--", "N" "-.", "O" "---", "P" ".--.", "Q" "--.-", "R" ".-.", "S" "...", "T" "-", "U" "..-", "V" "...-", "W" ".--", "X" "-..-", "Y" "-.--", "Z" "--..", "1" ".----", "2" "..---", "3" "...--", "4" "....-", "5" ".....", "6" "-....", "7" "--...", "8" "---..", "9" "----.", "0" "-----", " " " "})

;; (defn morseAscii [code]
;;   (let [code-chars (re-seq #"[^\s]+" code)]
;;     (clojure.string/join
;;      (map #(get morse-code-map %) code-chars))))


;; (defn -main
;;   [& args]
;;   ; decision here
;;   (println "Press 1 for ASCII-Morse or 2 for Morse-ASCII")
;;   (morseAscii "... . .-.. .-.. ---")
;;   (let [selection (Integer/parseInt (read-line))] 
;;     (println "Please supply a value.")
;;     (cond
;;       (= selection 1) (let [guess (read-line)]
;;                          (println (asciiMorse guess)))
;;       (= selection 2) (let [guess (read-line)]
;;                         (println "HERE COMES THE ANSWER")
;;                         (println (morseAscii guess)))
;;       )))
;; ; :else (println "Sorry, please select 1 or 2"))

(def morse-code-map {"A" ".-", "B" "-...", "C" "-.-.", "D" "-..", "E" ".", "F" "..-.", "G" "--.", "H" "....", "I" "..", "J" ".---", "K" "-.-", "L" ".-..", "M" "--", "N" "-.", "O" "---", "P" ".--.", "Q" "--.-", "R" ".-.", "S" "...", "T" "-", "U" "..-", "V" "...-", "W" ".--", "X" "-..-", "Y" "-.--", "Z" "--..", "1" ".----", "2" "..---", "3" "...--", "4" "....-", "5" ".....", "6" "-....", "7" "--...", "8" "---..", "9" "----.", "0" "-----", " " " "})

(defn morse-code-decoder [code]
  (let [code-chars (re-seq #"[^\s]+" code)]
    (clojure.string/join
     (map #(get morse-code-map %) code-chars))))
(defn -main
  [& args]
  (println (morse-code-decoder ".... . .-.. .-.. --- / -.. .- .. .-.. -.-- / .--. .-. --- --. .-. .- -- -- . .-. / --. --- --- -.. / .-.. ..- -.-. -.- / --- -. / - .... . / -.-. .... .- .-.. .-.. . -. --. . ... / - --- -.. .- -.--"))
)
