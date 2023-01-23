(ns functional-assignment.core
  (:gen-class) 
  (:require [clojure.java.io :as io]
            [clojure.tools.reader.edn :as edn]
            [clojure.string :as str]
            [clojure.spec.alpha :as s]))
; define a map
; need to get it in one function / map
; currently has to be upper case - fix this (possibly on taking input)
; this is very simple but will do for now

; probably need to split on characters as well as words (fun)
; try and get the spacing right

; define the morse code regex and do spec for it

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
; spec for both conversions, and tests
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


; Q2



; fix use of read-string - not safe
; which regex to split?
; (defn dataGrabber
;   [x]
;   (let [temp-data (slurp "dailyMeanLegacy.txt")
;         temps-strings (clojure.string/split temp-data #"\\s+")
;         temps (map read-string temps-strings)
;         ]
;     (println temps)
;     (println x)
;     ))

;( def tempValues)
; create map


; def what to do with each line


; split, average 
; could chop off the first bit
(defn averager [x]
  (let [line (subs x 10 69) 
        trimstrings (clojure.string/trim line)
        numberstrings (clojure.string/split trimstrings #"\s+")
        numbers (mapv edn/read-string numberstrings)
        result (apply + numbers)
        average (/ result 12)
        ]
    (println "numberstrings is: " numberstrings) 
    (println "trimstrings is: " trimstrings)
    (println "numbers is: " numbers)
    (println "result is: " result)
    (println "average is: " average)
    )
)

  
    ;(println(clojure.string/split (subs x 10) #"\s+")))

; add new values on whitespace
; thus calculating an average
;subvec not substring?


; every 70 characters?
; remove nils, -999s
; run this every how many times - use x as iterator value
; always a nil at the end for some reason

; println side effect 

; this just needs to take a line
; (defn dataGrabber [x, y]
;   (let [file "dailyMeanLegacy.txt"
;         values (str/split (slurp file) #"\s+")
;         lineStart x
;         lineEnd y
;         line (subvec values lineStart lineEnd)
;         date (subvec values 0 2)
;         temps (subvec values 2 15) 
;         numbers (mapv edn/read-string temps)
;         result (apply + numbers)
;         average (/ result 12)]
;     (println "This is the line " line)
;     (println "These is the date " date)
;     (println "These are the temperature values " temps)
;     (println "This is the result " result)
;     (println "This is the average for that day " average))
;   )
; removal of the 999s needs to happen afterwards or breaks iteration
; needs to be applied to every value in the thing i guess

; def vectorOfMonths


; inspired by https://stackoverflow.com/questions/16381835/given-a-clojure-vector-iteratively-remove-1-element
(defn bye999 [line] 
  (println line)
  (remove #{-999} line))
; if we can get this function to work, apply it to numbers

(defn byeValue [line value]
  (remove #{value} line))

(defn byeNil [line]
  (remove #{nil?} line))
; ;https://stackoverflow.com/questions/36359849/how-to-remove-sequential-matches-in-vector-in-clojure
; (defn remove-999 [v]
;   (mapv str (clojure.string/replace (apply str v) #"-999" "")))


; record for the map? deffo a spec

; to get a monthly average, pick each n value and repeat every n values until end of DS
; then remove 999s
; then calculate averages

; this is the function to add it to the wider DS

; yearAverage function

; dateVector (for map keys)
; whatevervaluesVector

; to get a monthly average, pick each n value and repeat every n values until end of DS
; then remove 999s
; then calculate averages
; remember to record days
(defrecord TempData [year month day value])
(s/def ::year int? )
(s/def ::month int? )
(s/def ::day int? )
(s/def ::value double? )



(defn warmestMonth [n line]
  (let [dropLine (drop 1 line)
        date (nth dropLine 0)
        day (nth dropLine 1)
        tempVec (vec dropLine)
        temps (subvec tempVec 2 14)
        monthTemp (subvec temps (+ n 1) (+ n 2))
        ] 
      (println "The month is "monthTemp);
  )
)


; can't take 999s out before we account for them


; pass this a vector of temps for a month


(defn warmestMonth [n line]
  (let [temps (vec line)
        
        monthTemp (subvec temps (+ n 1) (+ n 2))
        ] 
      (println "The month is "monthTemp);
  )
)



(defn dayAverage [line]
  (let [dropLine (drop 1 line)
        date (nth dropLine 0)
        day (nth dropLine 1)
        tempVec (vec dropLine)
        temps (subvec tempVec 2 14) ; should be a way to do this as sequence. adding should be easier too
        numbers (mapv edn/read-string temps)
        no999s (bye999 numbers)
        result (apply + no999s)
        average (/ result (count no999s)) yearVec []] ; this will need to be flexible, depending on how many 999s have been removed
    ; append average, date/day to map
        ;numbers (mapv edn/read-string temps)
        ;result (apply + numbers)
        ;average (/ result 12)]
    ; (println "This is the line " line)
    ; (println "This is the line " dropLine)
    (println "This is the result for the year " date " and the day " day)
    (println "These are the temperature values, excluding negative 999 results " no999s)
    ;(println "These are the numbers " numbers)
    ;(println "These are the values without negative 999 " no999s)
    ;(println "This is the result " result)
    (println "This is the average temperature for that day of the month, across the year " average)
    
    ; to be added up 31 times to get the yearly average, like below
    ; (let [yearVec [] result 0]
    ;   (if (< (edn/read-string day) 31) 
    ;     (conj yearVec no999s)
    ;     result (apply + yearVec)
    ;     )
    ;   ) 
  )
)
    
    ; (if (< (edn/read-string day) 31)
    ;   (let [yearVec [] result 0]
    ;     (conj yearVec no999s)
    ;     result (apply + yearVec)
    ;   )
    ;     (println "The year is " date " and the total is " yearVec) 
    ;     )
  ; add average, year to map
  ; then the map can be used for the calculations
  ; this is where we'll get spec in 

; (def temperatureValues
;   {:year "" (s/conform )
;    :month ""
;    :day ""
;    :value ""
;    })

; keys vector?

; function to return a map

; (defn keyValues []
;   [0 1 2 3 4 5 6 7 8 9 10 11 12]
; )

; (def valuesMap
;   zipmap()
;   )

; can probably get this algorithmically but use manual for now 
(def yearKeys
  [1772 1773 1774 1775 1776 1777 1778 1779 1780 1781 1782 1783 1784 1785 1786 1787 1788 1789 1790 1791 1792 1793 1794 1795 1796 1797 1798 1799
   1800 1801 1802 1803 1804 1805 1806 1807 1808 1809 1810 1811 1812 1813 1814 1815 1816 1817 1818 1819 1820 1821 1822 1823 1824 1825 1826 1827 1828 1829 1830 1831 1832 1833 1834 1835 1836 1837 1838 1839 1840 1841 1842 1843 1844 1845 1846 1847 1848 1849 1850 1851 1852 1853 1854 1855 1856 1857 1858 1859 1860 1861 1862 1863 1864 1865 1866 1867 1868 1869 1870 1871 1872 1873 1874 1875 1876 1877 1878 1879 1880 1881 1882 1883 1884 1885 1886 1887 1888 1889 1890 1891 1892 1893 1894 1895 1896 1897 1898 1899 
   1900 1901 1902 1903 1904 1905 1906 1907 1908 1909 1910 1911 1912 1913 1914 1915 1916 1917 1918 1919 1920 1921 1922 1923 1924 1925 1926 1927 1928 1929 1930 1931 1932 1933 1934 1935 1936 1937 1938 1939 1940 1941 1942 1943 1944 1945 1946 1947 1948 1949 1950 1951 1952 1953 1954 1955 1956 1957 1958 1959 1960 1961 1962 1963 1964 1965 1966 1967 1968 1969 1970 1971 1972 1973 1974 1975 1976 1977 1978 1979 1980 1981 1982 1983 1984 1985 1986 1987 1988 1989 1990 1991 1992 1993 1994 1995 1996 1997 1998 1999 
   2000 2001 2002 2003 2004 2005 2006 2007 2008 2009 2010 2011 2012 2013 2014 2015 2016 2017 2018 2019 2020 2021 2022
])
(defn getMap [vals]
  (let [mapReturn (zipmap (map keyword yearKeys) (vec vals))] mapReturn)
  )
; other way of doing it
; get index and compare against yearKeys, thus returning the year





; EOLs are ending up on the next line

; get the multiples of x (3-15) for each calendar month in the vector, and add them up (to get averages for each calendar month ever - Q2A)

; iterate over the 12, '31?' times to get all values in a year (so do 'dayAverage' 31 times on values), average them up and use for Q2B - warmest/coldest year

; get the averages for all years 

; this spits out the average for year X
; we then throw it into vector to (in theory) get all the averages
(defn yearAverage [year line]
  ; get rid of all the actual year stuff
  (let [dropLine (drop 1 line) dropVec (vec dropLine) yearValue (subvec dropVec 0 1) numbers (mapv edn/read-string dropLine) yearLess (byeValue numbers (Integer/parseInt (get yearValue 0))) nineLess (bye999 yearLess) result (apply + nineLess) sanitisedResult (- result 496) divider (- (count nineLess) 31) average (/ sanitisedResult divider) degreeAverage (double (/ average 10)) intDegreeAverage (num degreeAverage) yearKey (str (get yearValue 0))]
  ; more here
    ; sanitise the count - need to take 31 from it
    ; change year function to remove all
    (println "Full data for " yearValue " " nineLess)
    ; curtail to 2dp 
    ; create and return map pair here?  
    
    (println "This is the result for the year " yearValue ". The sum is " sanitisedResult "and the average is " average " tenths of a degree, and " (double (/ average 10)) " degrees")
    ; return the thing to be added to the map instead 
    intDegreeAverage)
    )



; warmest 2014 : 10.910.9479452054 whatever degrees
; coldest 2022:  7.056666666666667 degrees (influenced by only winter months being available)
; now to figure out a code based comparison


; file "dailyMeanLegacy.txt"
;         values (str/split (slurp file) #"\s+")
;         lineStart x
;         lineEnd y
;         line (subvec values lineStart lineEnd)

;(dorun (map println(subvec values 0 15)
  ; (with-open [r (io/reader "dailyMeanLegacy.txt")]
  ; (doseq [line (line-seq r)]
  ;   (println (subs line 1 5)) ; this is how we get the key value maybe? tells us the year. set to var, add to map. average all values in year, month, etc
  ;   (println (subs line 9 10)) ; this gives the day value
  ;   ; the rest gives the 12 temp values for each month. use the iterator to determine which month it is 
  ;   (println (clojure.string/split line #"\s+" )) 
  ;   (println (averager line))
  ;   ) 
  ;   )

  ; add to map here 

; load everything into one
; then line by line to the map
; 0-15, repeatedly

; we have the pieces - need to figure out how to iterate through them

; basically, iterate through big values data structure
; add to map
; call function for whatever calculation on the line - feed the function the line
; once this is done, do the spec




; you probably need to take the loop/recur thing from the tab opposite
; and use that

(defn addToVector
  [] 
  (loop [i 0 sum []]
    (if (< i 10)
      (recur (inc i) (conj sum 10))
      sum)))

; to get the sum of a line, maybe?
(defn sumVector [v]
   (loop [v v sum 0]
   (if (empty? v)
       sum
        (recur (rest v) (+ sum (first v))))))

(def condj ((remove nil?) conj))

(defn sum [coll] (reduce + coll))

(defn find-max [v]
  (letfn [(max? [v m]
          (if (empty? v)
            m
            (if (> (first v) m)
              (recur (rest v) (first v))
              (recur (rest v) m))))]
    (max? v (first v))))

; https://stackoverflow.com/questions/59584466/can-avoiding-nil-conjoins-be-done-better

(defn -main
  [& args]
  ; decision here
  (println "Press 1 for ASCII-Morse or 2 for Morse-ASCII. Press 4 to find the warmest and coldest years.")
  (let [selection (Integer/parseInt (read-line))] 
    (println "Please supply a value.")
    (cond
      (= selection 1) (let [guess (read-line)]
                         (println (asciiMorse (clojure.string/upper-case guess))))
      (= selection 2) (let [guess (read-line)]
                        (println (morseAscii (clojure.string/upper-case guess))))
      (= selection 3)
      (let [file "dailyMeanLegacy.txt" values (str/split (slurp file) #"\s+") n (Integer/parseInt(read-line))
                        line (loop [structure (vec values) emptyVec [] lineStart 0 lineEnd 14] ; for year accumulation will probably need to be done at this level (take them all into vectors of size 31*14?)
                                   (if (empty? structure) 
                                     emptyVec 
                                     ; fix this to avoid EOF crashing - we'll need to change it to return a final value at the end.
                                     (recur
                                      (next structure)
                                      (let [line (subvec values lineStart (+ lineEnd 1))  tempValue (nth line (+ 2 n) 0 )]
                                        (reduce + emptyVec)
                                        (conj emptyVec tempValue)
                                        ; bear in mind that the last value is not added - not sure why. could be mitigated with if, possibly (as coldest should be 2022)
                                        ; we can now access the yearAverage value
                                        ; (println "Return value of yearAverage" (yearAverage n line))
                                        (let [nilVec (remove nil? emptyVec)]
                                          ; wrap in an if statement to only print at the end
                                          (println "Temp:" tempValue)
                                          (println "Vector: " nilVec)
                                          ; supply not-found value to nth to avoid breaking
                                          ; do map stuff here maybe? send the nilVec to a function that matches it to a yearKey
                                          ; default values to avoid breaking when vec has nothing in it
                                          ; this should be 2022 but we're not picking it up (not added to vector for some reason) 2022 also doesn't have full data
                                          ;(println "Minimum index" minIndex)
                                          ))
                             ; replace with year average - THIS IS WHERE WE ADD (some form of conj working with empty lin) ; throw to map - ideally remove nil first ; find highest/lowest and return with their year compatriot
                                      (+ lineStart 14) (+ lineEnd 14)
                                      )
                                     ))
                            ])
      ; (
      ;                  let [file "dailyMeanLegacy.txt" values (str/split (slurp file) #"\s+") n (Integer/parseInt(read-line))
      ;                       line (loop [structure (vec values) emptyLine [] lineStart 0 lineEnd 14] ; for year accumulation will probably need to be done at this level (take them all into vectors of size 31*14?)
      ;                              (if (empty? structure) 
      ;                                emptyLine 
      ;                                ; fix this to avoid EOF crashing
      ;                                (recur
      ;                                 (next structure)
      ;                                 (let [line (subvec values lineStart (+ lineEnd 1)) tempValue (nth line (+ 2 n) 0 )] 
      ;                                   ; (dayAverage line)
      ;                                   (println "Temp value" tempValue)
      ;                                   (conj emptyLine tempValue) 
      ;                                   (let [numbers (mapv edn/read-string emptyLine) no999s (bye999 numbers)] 
      ;                                     (println "MONTH" no999s) )) 
      ;                                 (+ lineStart 14) (+ lineEnd 14)) ))]) 
      ; grabby yeary
      ; row by row approach
      (= selection 4) (let [file "dailyMeanLegacy.txt" values (str/split (slurp file) #"\s+") n (Integer/parseInt(read-line))
                        line (loop [structure (vec values) emptyVec [] lineStart 0 lineEnd 434] ; for year accumulation will probably need to be done at this level (take them all into vectors of size 31*14?)
                                   (if (empty? structure) 
                                     emptyVec 
                                     ; fix this to avoid EOF crashing - we'll need to change it to return a final value at the end.
                                     (recur
                                      (next structure)
                                      (let [line (subvec values lineStart (+ lineEnd 1))]
                                        (conj emptyVec (yearAverage n line))
                                        ; bear in mind that the last value is not added - not sure why. could be mitigated with if, possibly (as coldest should be 2022)
                                        ; we can now access the yearAverage value
                                        (println "Return value of yearAverage" (yearAverage n line))
                                        (let [nilVec (remove nil? emptyVec) maximum (apply max 0 nilVec) minimum (apply min 100 nilVec) maxIndex (.indexOf nilVec maximum) minIndex (.indexOf nilVec minimum)]
                                          ; wrap in an if statement to only print at the end
                                          (println "Vector: " nilVec)
                                          ; supply not-found value to nth to avoid breaking
                                          (println "The warmest year was" (nth yearKeys maxIndex 0) "and the temperature was"maximum "degrees")
                                          ; do map stuff here maybe? send the nilVec to a function that matches it to a yearKey
                                          ; default values to avoid breaking when vec has nothing in it
                                          (println "The coldest year we have full data for was" (nth yearKeys minIndex 0) "and the temperature was"minimum" degrees")
                                          ; this should be 2022 but we're not picking it up (not added to vector for some reason) 2022 also doesn't have full data
                                          ;(println "Minimum index" minIndex)
                                          (println "Map" (getMap nilVec)))
                                        (let [bigConj (yearAverage n line)]
                                          (println "This is the big conj " bigConj) (conj emptyVec bigConj (println "THE CONJINATOR" emptyVec))))
                             ; replace with year average - THIS IS WHERE WE ADD (some form of conj working with empty lin) ; throw to map - ideally remove nil first ; find highest/lowest and return with their year compatriot
                                      (+ lineStart 434) (+ lineEnd 434)
                                      )
                                     ))
                            ]))
                               ;(let [currentMap (getMap emptyLine)] (println "max value is" (apply max-key #(val (first %)) currentMap))))
   
   ; (println "max value is" (apply max-key #(val (first %)) currentMap))
   ; remove nil? emptyVec
                                      ;                                     ;                (let [currentMap (getMap currentMap)] (println "MAP MAP " currentMap))
                                      ;            ) (println "SECOND LINE" currentMap)))  ; replace with year average - THIS IS WHERE WE ADD (some form of conj working with empty lin) ; throw to map - ideally remove nil first ; find highest/lowest and return with their year compatriot
                                      ; (+ lineStart 434) (+ lineEnd 434))))]))
                                                                          ;
      ; (= selection 4) (let [file "dailyMeanLegacy.txt" values (str/split (slurp file) #"\s+") n (Integer/parseInt(read-line)) structure (vec values) lineStart 0 lineEnd 14]
      ;                     (loop [i 0 iterator (+ 2 n) monthData [] vecData structure]
      ;                       (if ( < i 108933)
      ;                         (recur
      ;                          (next structure)
      ;                          (let [line (subvec vecData lineStart (+ lineEnd 1))]
      ;                            )
      ;                          (inc i)
      ;                          (conj monthData (get structure iterator)) (println (get vecData iterator)) (* iterator i)) monthData)
      ;                       ))
   ))
                  
                              
                              ;(recur (inc i) (let [month (nth structure (* iterator i))] (conj monthData month)) monthData)      
      
      ;                       line (loop [structure (vec values) emptyLine [] lineStart 0 lineEnd 14 monthValues[]] 
      ;                              (if (empty? structure) 
      ;                                emptyLine 
      ;                                ; fix this to avoid EOF crashing
      ;                                (recur
      ;                                 (rest structure)
      ;                                 (let [line (subvec values lineStart (+ lineEnd 1)) 
      ;                                       ; dropLine (drop 1 line) 
      ;                                       ; date (nth dropLine 0) 
      ;                                       ; day (nth dropLine 1) 
      ;                                       ; tempVec (vec dropLine) 
      ;                                       ; temps (subvec tempVec 2 14) ; should be a way to do this as sequence. adding should be easier too 
      ;                                       ; numbers (mapv edn/read-string temps)
      ;                                       iterator (+ 2 n) secondIterator (+ 3 n) 
      ;                                       month (subvec line iterator secondIterator) megamonth (conj monthValues month)  ]
                                        
      ;                                   ; (dayAverage line)
      ;                                   (println "This is the month value for the given line" month)
      ;                                   (println "These are the month values so far" megamonth " and iterators" lineStart lineEnd)
      ;                                   ) 
      ;                                 (+ lineStart 14) (+ lineEnd 14) monthValues)))] )
      ; )))
                            ; iterate here
                            ; call function / add to map
                            
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

