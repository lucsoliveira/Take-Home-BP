(ns takehome.rules
  (:require [java-time :as time]))

; UTILS

; This function verify if the content released-at are between
; subscription end and start of the buyer account
(defn released-between-begin-end-registration?[object purchase]

  (if (time/before? (:subscription-start purchase) (:released-at object) (:subscription-end purchase))
    true
    false))

; This function verify if the purchaser still
; registered compared with the actual date
(defn user-still-registered? [purchase]

  (if (time/before?  (java.time.LocalDateTime/now) (:subscription-end purchase))
    true
    false))


; Rules

; Rules access for Patriota
(defn patriota-rules [object purchase]

  (cond
    (= (:type object) :movie) (user-still-registered? purchase)
    (= (:type object) :series) (user-still-registered? purchase)
    (= (:type object) :podcast) (user-still-registered? purchase)
    (= (:type object) :debate) (user-still-registered? purchase)
    (= (:type object) :interview) (or (user-still-registered? purchase) (released-between-begin-end-registration? object purchase) )
    (= (:type object) :course) false
    (= (:type object) :patron) false
    :else false))

; Rules access for Premium
(defn premium-rules [object purchase]

  (cond
    (= (:type object) :movie) (user-still-registered? purchase)
    (= (:type object) :series) (user-still-registered? purchase)
    (= (:type object) :podcast) (user-still-registered? purchase)
    (= (:type object) :debate) (user-still-registered? purchase)
    (= (:type object) :interview) (user-still-registered? purchase)
    (= (:type object) :course) (user-still-registered? purchase)
    (= (:type object) :patron) false
    :else false))
