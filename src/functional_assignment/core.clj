(ns functional-assignment.core
  (:gen-class))
(require '[clojure.string :as str])
; define a map
; need to get it in one function / map
; currently has to be upper case - fix this (possibly on taking input)
; this is very simple but will do for now

; probably need to split on characters as well as words (fun)
; try and get the spacing right
(defn asciiMorse [s]
  (let [morse-map {"A" ".-", "B" "-...", "C" "-.-.", "D" "-..", "E" ".",
                   "F" "..-.", "G" "--.", "H" "....", "I" "..", "J" ".---",
                   "K" "-.-", "L" ".-..", "M" "--", "N" "-.", "O" "---",
                   "P" ".--.", "Q" "--.-", "R" ".-.", "S" "...", "T" "-",
                   "U" "..-", "V" "...-", "W" ".--", "X" "-..-", "Y" "-.--",
                   "Z" "--..", " " "       "}]
    (clojure.string/join "   " (map morse-map (str/split s #"")))))
(def morse-code-map {".-" "A", "-..." "B", "-.-." "C", "-.." "D", "." "E", "..-." "F", "--." "G", "...." "H", ".." "I", ".---" "J", "-.-" "K", ".-.." "L", "--" "M", "-." "N", "---" "O", ".--." "P", "--.-" "Q", ".-." "R", "..." "S", "-" "T", "..-" "U", "...-" "V", ".--" "W", "-..-" "X", "-.--" "Y", "--.." "Z", ".----" "1", "..---" "2", "...--" "3", "....-" "4", "....." "5", "-...." "6", "--..." "7", "---.." "8", "----." "9", "-----" "0", "       " " "})
 
; check seven spaces 


; what does a morse-ASCII need to do?
; needs to take morse as a whole string
; split into characters (3 spaces) and words (7 spaces)
; one function to convert one character into ascii?

; first parse into words
; then parse into the individual characters?
; sequence?
; split on 7 spaces 

; function to split into words?
(defn morseWordGrabber [m]
  (str/split m #"       "))

(defn morseAscii [morse]

  ; need the split on seven spaces before splitting on 3
  (clojure.string/join "" (map morse-code-map (str/split morse #"   ")(str/split morse #"   "))))
; just want to access the other part of it surely?
; not accepting the morse as input - interesting
; (defn morseAscii [code]
;   (let [code-chars  (re-seq #"[^\s]+" code)] 
;     (clojure.string/join " "
;      (map #(get morse-code-map %) code-chars)))) 

; difference is this is a string of morse - not a single character (Except in rare situations) and must be a complete match
; (defn morseAscii [morse] 
;   (for [k v] 
;     :when (some #(= morse %) (morse-code-map))]
;     (println (k)))
; )
; throw all input to upper case
(defn -main
  [& args]
  ; decision here
  (println "Press 1 for ASCII-Morse or 2 for Morse-ASCII")
  (let [selection (Integer/parseInt (read-line))] 
    (println "Please supply a value.")
    (cond
      (= selection 1) (let [guess (read-line)]
                         (println (asciiMorse (clojure.string/upper-case guess))))
      (= selection 2) (let [guess (read-line)]
                        (println (morseAscii (clojure.string/upper-case guess))))
      )))
; :else (println "Sorry, please select 1 or 2"))

; legacy format - first column year, second day and month, temps in 1/10 degree
; think about data structure
; use legacy NOT easy
; also need the last 4 files as 1772 to date DOES NOT include
;-32768 for missing data
; first 2 rows can be superflous (averages, etc) - look for the first

; (defn roman-digit-translator [digit]
; (first
;    (filter #(>= digit (first %))
;      [[1000 "M" ]
;       [500  "D" ]
;       [400  "CD"]
;       [100  "C" ]
;      [90   "XC"]
;       [50   "L" ]
;       [40   "XL"]
;       [10   "X" ]
;       [9    "IX"]
;       [5    "V" ]
;       [4    "IV"]
;      [1    "I" ]])))

; (defn roman [n]
; (when (> n 0)
; (let [[n-diff romanNumeral] (roman-digit-translator n)]
;    (apply str romanNumeral(roman(- n n-diff))))))

; (println(roman 9))


; (def numerals {\I 1, \V 5, \X 10, \L 50, \C 100, \D 500, \M 1000})

; (defn numeral-grabber [n y]
;  (if (> n (* 4 y)) (- n y) (+ y n)))

; (defn roman [d]
;  (reduce numeral-grabber (map numerals (reverse d))))

; (println(roman "MCL"))