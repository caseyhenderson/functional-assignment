(ns functional-assignment.core
  (:gen-class) 
  (:require [clojure.tools.reader.edn :as edn]
            [clojure.string :as str]
            [clojure.spec.alpha :as s]))

; (def morse-regex #"[.-]{1,5}(?> [.-]{1,5})*(?>   [.-]{1,5}(?> [.-]{1,5})*)*")
; ; Morse validation regex taken from https://stackoverflow.com/questions/17197887/java-regexp-match-morse-code
; (def ascii-regex #"[ -~]+")
; Morse and ASCII specifications to ensure accurate conversions
; Ran out of time to use these in specs

; Simpler specs instead
; Ensure what's entering and leaving our morse-ASCII functions is the right type
(s/def ::morse-code string?)
(s/def ::ascii-spec string?)
; Function definition for ASCII-to-morse
(s/fdef ascii-to-morse
  :args (s/and (s/cat :s string?))
  :ret string?
  )
; Check what's coming into our year average function is seqable, or we won't be able to do the necessary operations on it.
(s/def ::year-average-function seqable?)

(def ascii-morse-map "{ 'A':'.-', 'B':'-...',
                    'C':'-.-.', 'D':'-..', 'E':'.',
                    'F':'..-.', 'G':'--.', 'H':'....',
                    'I':'..', 'J':'.---', 'K':'-.-',
                    'L':'.-..', 'M':'--', 'N':'-.',
                    'O':'---', 'P':'.--.', 'Q':'--.-',
                    'R':'.-.', 'S':'...', 'T':'-',
                    'U':'..-', 'V':'...-', 'W':'.--',
                    'X':'-..-', 'Y':'-.--', 'Z':'--..',
                    '1':'.----', '2':'..---', '3':'...--',
                    '4':'....-', '5':'.....', '6':'-....',
                    '7':'--...', '8':'---..', '9':'----.',
                    '0':'-----'}")
; altered post-video to remove unecessary characters that aren't in the minimal standard character set.

; Deals with making a Clojure map out of the above, for use in the conversions
(def morse-map (read-string (str/replace 
                     (str/replace 
                      (str/replace 
                       (str/replace ascii-morse-map
                                    "\n" "") 
                       "                    " " ")
                      ":" " ")
                     "'" "\"")))

; ASCII-to-morse conversion, splits and grabs the relevant morse values from the map
(defn ascii-to-morse [s]
  {:pre [(s/valid? ::ascii-spec s)]
   :post [(s/valid? ::ascii-spec s)]}
  (str/join " "
            (map #(get morse-map (str/upper-case %)) (str/split s #""))))

; Morse to ASCII conversion
(defn morse-to-ascii [morse-ascii]
  {:pre [(s/valid? ::morse-code morse-ascii )]
   :post [(s/valid? ::morse-code morse-ascii)]}
    (str/join (map #(get 
                     (zipmap (vals morse-map) (keys morse-map)) % " ") 
                   (str/split morse-ascii #" "))))
; inspired by https://stackoverflow.com/questions/74956079/convert-input-string-into-morse-code-with-clojure
; results also validated by using https://onlineasciitools.com/convert-ascii-to-morse
 
; Helper functions
(defn remove-999 [line] 
  (remove #{-999} line))
; inspired by https://stackoverflow.com/questions/16381835/given-a-clojure-vector-iteratively-remove-1-element

(defn remove-value [line value]
  (remove #{value} line))

(defn month-lookup [m]
  (let [months ["January" "February" "March" "April" "May" "June" "July" "August" "September" "October" "November" "December"]  ]
    (get months m)))
; Example spec of temperature value
(defrecord TempData [year month day value])
(s/def ::year int? )
(s/def ::month int? )
(s/def ::day int? )
(s/def ::value double? )

; Q2 
(def year-keys
  [1772 1773 1774 1775 1776 1777 1778 1779 1780 1781 1782 1783 1784 1785 1786 1787 1788 1789 1790 1791 1792 1793 1794 1795 1796 1797 1798 1799
   1800 1801 1802 1803 1804 1805 1806 1807 1808 1809 1810 1811 1812 1813 1814 1815 1816 1817 1818 1819 1820 1821 1822 1823 1824 1825 1826 1827 1828 1829 1830 1831 1832 1833 1834 1835 1836 1837 1838 1839 1840 1841 1842 1843 1844 1845 1846 1847 1848 1849 1850 1851 1852 1853 1854 1855 1856 1857 1858 1859 1860 1861 1862 1863 1864 1865 1866 1867 1868 1869 1870 1871 1872 1873 1874 1875 1876 1877 1878 1879 1880 1881 1882 1883 1884 1885 1886 1887 1888 1889 1890 1891 1892 1893 1894 1895 1896 1897 1898 1899 
   1900 1901 1902 1903 1904 1905 1906 1907 1908 1909 1910 1911 1912 1913 1914 1915 1916 1917 1918 1919 1920 1921 1922 1923 1924 1925 1926 1927 1928 1929 1930 1931 1932 1933 1934 1935 1936 1937 1938 1939 1940 1941 1942 1943 1944 1945 1946 1947 1948 1949 1950 1951 1952 1953 1954 1955 1956 1957 1958 1959 1960 1961 1962 1963 1964 1965 1966 1967 1968 1969 1970 1971 1972 1973 1974 1975 1976 1977 1978 1979 1980 1981 1982 1983 1984 1985 1986 1987 1988 1989 1990 1991 1992 1993 1994 1995 1996 1997 1998 1999 
   2000 2001 2002 2003 2004 2005 2006 2007 2008 2009 2010 2011 2012 2013 2014 2015 2016 2017 2018 2019 2020 2021 2022
])

(defn year-average [line] 
  {:pre [(s/valid? ::year-average-function line )]}
  ; Here, we take the line input and extract the actual year data from it to calculate the relevant values.
  ; A number of mathematical operations are required to remove the effects of -999s and the day indicators from the data 
  (let [dropLine (drop 1 line) dropVec (vec dropLine) yearValue (subvec dropVec 0 1) 
        numbers (mapv edn/read-string dropLine) 
        yearLess (remove-value numbers (Integer/parseInt (get yearValue 0))) 
        nineLess (remove-999 yearLess) result (apply + nineLess) 
        sanitisedResult (- result 496) 
        divider (- (count nineLess) 31) average (/ sanitisedResult divider) 
        degreeAverage (double (/ average 10)) intDegreeAverage (num degreeAverage)]
    ; curtail to 2dp 
    (println "This is the result for the year" yearValue ". The average temperature for this year is"(format "%.2f"(double (/ average 10))) " degrees")
    intDegreeAverage)
    )
; warmest 2014 : 10.910.9479452054 degrees
; coldest 2022:  7.056666666666667 degrees (influenced by only winter months being available - otherwise it's 1879)

(defn -main
  [& args]
  ; take input for the user choice
  (println "Press 1 for ASCII-Morse or 2 for Morse-ASCII. Press 3 to find the warmest day and mean average temp for the given calendar month n (2a, 2c). Press 4 to find the warmest and coldest years.(2b)")
  (let [selection (Integer/parseInt (read-line))] 
    (println "Please supply a value.")
    (cond
      (= selection 1) (let [input (read-line)]
                         (println (ascii-to-morse (clojure.string/upper-case input))))
      (= selection 2) (let [input (read-line)]
                        (println (morse-to-ascii (clojure.string/upper-case input))))
      (= selection 3)
      (let [file "dailyMeanLegacy.txt" values (str/split (slurp file) #"\s+") n (Integer/parseInt(read-line)) structure (vec values) ] 
        ; start at the first value for the given month, and build a vector of all values for said month, row-by-row
        ; all temp values outputted in degrees to 2dp for usability.
        (let [sanitised (drop 1 structure) sanVec (vec sanitised)
              finalVec (subvec sanVec (+ 1 n) (count sanitised)) monthValues (take-nth 14 finalVec) 
              months (mapv edn/read-string monthValues) 
              no999s (remove-999 months) result (apply + no999s) 
              mean (/ result (count no999s)) maximum (apply max 0 months)
              minimum (apply min 100 no999s) maxIndex (+ 1 (.indexOf months maximum)) yearBasis (/ maxIndex 31) 
              yearValue (int(+ 1772 yearBasis)) dayValue (rem maxIndex 31)
              maxFromMean (- maximum mean) minFromMean(- mean minimum) minIndex (.indexOf months minimum)
              yearMinBasis ( / minIndex 31) yearMinValue (int(+ 1722 yearMinBasis)) dayMinValue (rem minIndex 31)
              month (month-lookup (- n 1))]
          (println "Month number: " n) ; translate to month text, eventually. we'll use a lookup function. could define as variable 
          (println "Warmest temp in month:"(format "%.2f"(double(/ maximum 10)))"degrees")
          (println "The warmest day for the calendar month of"month"is"dayValue""month""yearValue)
          (println "Mean average temp for the month of"month":"(format "%.2f"(double(/ mean 10))) "degrees")
          (println "Difference of maximum temperature["(format "%.2f"(double(/ maximum 10)))"]degrees from mean is"(format "%.2f"(double(/ maxFromMean 10))) "degrees")
          (println "Difference of the minimum temperature["(format "%.2f"(double(/ minimum 10)))"]degrees from mean is"(format "%.2f"(double(/ minFromMean 10)))"degrees")
          (if (> minFromMean maxFromMean)
            (println "Greatest variation from mean is"(format "%.2f"(double(/ minFromMean 10))) "degrees, on the date"dayMinValue""month""yearMinValue)
          (println "Greatest variation from mean is" (format "%.2f"(double(/ maxFromMean 10))) "degrees, on the date"dayValue""month""yearValue))
          ; Calculations: day value - value is remainder of index / 31, division by 31 and addition to 1772 gives the year (int values only)
          ; (println "Found at index:" maxIndex) Useful for verification of result
          ) 
          )
      (= selection 4) (let [file "dailyMeanLegacy.txt" values (str/split (slurp file) #"\s+")
                        line (loop [structure (vec values) emptyVec [] lineStart 0 lineEnd 434] ; for year accumulation will probably need to be done at this level (take them all into vectors of size 31*14?)
                                   (if (> lineEnd 108934) ; temp fix to avoid EOF crashing
                                     emptyVec 
                                     ; recur for each year
                                     (recur
                                      (next structure)
                                      (let [line (subvec values lineStart (+ lineEnd 1))]
                                        ; bear in mind that the last value is not added - not sure why. could be mitigated with if, possibly (as coldest should be 2022) 
                                        (let [nilVec (remove nil? emptyVec) maximum (apply max 0 nilVec) minimum (apply min 100 nilVec) maxIndex (.indexOf nilVec maximum) minIndex (.indexOf nilVec minimum)]
                                          ; wrap in an if statement to only print at the end
                                          ; supply not-found value to nth to avoid breaking
                                          (println "The warmest year was" (nth year-keys maxIndex 0) "and the temperature was"(format "%.2f"(double maximum)) "degrees")
                                          ; default values to avoid breaking when vec has nothing in it
                                          (println "The coldest year we have full data for was" (nth year-keys minIndex 0) "and the temperature was"(format "%.2f"(double minimum))"degrees")
                                          )
                                        (let [bigConj (year-average line)] 
                                          (conj emptyVec bigConj)))
                                      ; takes each year row-by-row
                                      (+ lineStart 434) (+ lineEnd 434)
                                      )
                                     ))
] (println "Only the winter data for 2022 was provided, hence it cannot be accurately compared to other years.") ; covers the 2022-case
))))
                  