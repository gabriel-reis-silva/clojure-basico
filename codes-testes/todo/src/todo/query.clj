(ns todo.query
  (:require [todo.database]
            [korma.core :refer :all]))

(defentity users)

(defn get-todos []
  (select users))

(defn add-todo [name email password]
  (insert users
          (values {:name name :email email})))

(defn delete-todo [id]
  (delete users
          (where {:id [= id]})))

(defn update-todo [id name is-complete]
  (update users
          (set-fields {:name name
                       :is_complete is-complete})
          (where {:id [= id]})))

(defn get-todo [id]
  (first
   (select users
           (where {:id [= id]}))))