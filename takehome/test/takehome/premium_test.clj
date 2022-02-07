(ns takehome.premium_test
  (:require [clojure.test :refer :all]
            [java-time :as time]
            [takehome.core :as sub]))

; Premium can access series only if your subscription still active
(deftest test-premium-series
  (are [result purchase] (= result
                            (sub/can-access?
                              {:type :series :name "Investigação Paralela", :released-at (time/local-date-time "2019-11-29T20:16:45.747")}
                              purchase))
                         ; before
                         false  {:type               :premium
                                 :subscription-start (time/local-date-time "2019-01-24T11:46:22.811")
                                 :subscription-end   (time/local-date-time "2020-01-24T11:46:22.811") }
                         ; between
                         false {:type               :premium
                                :subscription-start (time/local-date-time "2017-01-24T11:46:22.811")
                                :subscription-end   (time/local-date-time "2021-01-24T11:46:22.811") }
                         ; still active
                         true {:type               :premium
                               :subscription-start (time/local-date-time "2017-01-24T11:46:22.811")
                               :subscription-end   (time/local-date-time "2022-04-24T11:46:22.811") }))

; Premium can access podcasts only if your subscription still active
(deftest test-premium-podcasts
  (are [result purchase] (= result
                            (sub/can-access?
                              {:type :podcast :name "1917 - Podcast Cultura Paralela #8", :released-at (time/local-date-time "2020-03-29T20:02:34.367")}
                              purchase))
                         ; before
                         false  {:type               :premium
                                 :subscription-start (time/local-date-time "2018-01-24T11:46:22.811")
                                 :subscription-end   (time/local-date-time "2019-01-24T11:46:22.811") }
                         ; between
                         false {:type               :premium
                                :subscription-start (time/local-date-time "2020-01-24T11:46:22.811")
                                :subscription-end   (time/local-date-time "2021-01-24T11:46:22.811") }
                         ; still active
                         true {:type               :premium
                               :subscription-start (time/local-date-time "2021-01-24T11:46:22.811")
                               :subscription-end   (time/local-date-time "2022-04-24T11:46:22.811") }))

; Premium can access debates only if your subscription still active
(deftest test-premium-debates
  (are [result purchase] (= result
                            (sub/can-access?
                              {:type :debate :name "Qual o limite do Respeito?", :released-at (time/local-date-time "2020-03-29T20:02:34.351")}
                              purchase))
                         ; before
                         false  {:type               :premium
                                 :subscription-start (time/local-date-time "2018-01-24T11:46:22.811")
                                 :subscription-end   (time/local-date-time "2019-01-24T11:46:22.811") }
                         ; between
                         false {:type               :premium
                                :subscription-start (time/local-date-time "2020-01-24T11:46:22.811")
                                :subscription-end   (time/local-date-time "2021-01-24T11:46:22.811") }
                         ; still active
                         true {:type               :premium
                               :subscription-start (time/local-date-time "2022-01-24T11:46:22.811")
                               :subscription-end   (time/local-date-time "2023-04-24T11:46:22.811") }))

; Premium can access interview only if your subscription still active
(deftest test-premium-interview
  (are [result purchase] (= result
                            (sub/can-access?
                              {:type :interview :name "Congresso Brasil Paralelo - Rafael Nogueira", :released-at (time/local-date-time "2019-11-16T21:40:51.579")}
                              purchase))
                         ; before
                         false {:type               :premium
                                :subscription-start (time/local-date-time "2017-01-24T11:46:22.811")
                                :subscription-end   (time/local-date-time "2019-01-24T11:46:22.811") }
                         ; between
                         false  {:type               :premium
                                 :subscription-start (time/local-date-time "2019-01-24T11:46:22.811")
                                 :subscription-end   (time/local-date-time "2020-01-24T11:46:22.811") }
                         ; subscription still active
                         true {:type               :premium
                               :subscription-start (time/local-date-time "2022-01-24T11:46:22.811")
                               :subscription-end   (time/local-date-time "2023-01-24T11:46:22.811") }
                         ))

; Premium can access Cursos
(deftest test-premium-courses
  (are [result purchase] (= result
                            (sub/can-access?
                              {:type :course :name "Titãs da Civilização Ocidental", :released-at (time/local-date-time "2019-06-18T12:30:00.564")}
                              purchase))
                         ; before
                         false  {:type               :premium
                                 :subscription-start (time/local-date-time "2018-01-24T11:46:22.811")
                                 :subscription-end   (time/local-date-time "2019-01-24T11:46:22.811") }
                         ; between
                         false {:type               :premium
                                :subscription-start (time/local-date-time "2019-01-24T11:46:22.811")
                                :subscription-end   (time/local-date-time "2020-01-24T11:46:22.811") }

                         ; still active
                         true  {:type               :premium
                                :subscription-start (time/local-date-time "2022-01-24T11:46:22.811")
                                :subscription-end   (time/local-date-time "2023-01-24T11:46:22.811") }))

; Premium cannot access Patron content
(deftest test-premium-patron
  (are [result purchase] (= result
                            (sub/can-access?
                              {:type :patron :name "Relatório Mecenas", :released-at (time/local-date-time "2020-08-10T20:00:00.656")}
                              purchase))
                         ; before
                         false  {:type               :premium
                                 :subscription-start (time/local-date-time "2018-01-24T11:46:22.811")
                                 :subscription-end   (time/local-date-time "2019-01-24T11:46:22.811") }
                         ; between
                         false {:type               :premium
                                :subscription-start (time/local-date-time "2020-01-24T11:46:22.811")
                                :subscription-end   (time/local-date-time "2021-01-24T11:46:22.811") }

                         ; still active
                         false  {:type               :premium
                                 :subscription-start (time/local-date-time "2022-01-24T11:46:22.811")
                                 :subscription-end   (time/local-date-time "2023-01-24T11:46:22.811") }))