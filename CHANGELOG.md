# L22

情報リテラシー 2022 ポータルサイトを luminus/clojure で。

## Unreleased
- /admin admin のジョブはなんだ？ list-users, delete-user/:login
- l22.register のテストはどう書く？
- guthub

## 0.2.4-SNAPSHOT
- target="_blank" rel="noopener noreferrer"
- `/admin/users`
- password! のログを詳しく timbre/info で出す。

## 0.2.3 - 2022-03-18
### Added
- `admin.html`
- post `/admin/logout`
- access restriction about `/admin`

## 0.2.2 - 2022-03-18
### Added
- パスワードを変更後の home に赤でフラッシュメッセージを出した。
- `run.sh` 3022/tcp で動かすためのスタートアップスクリプト。
- `register.clj` 登録できたら flash
### Changed
- imporove `password.html`, bluma.

## 0.2.1 - 2022-03-18
### Added
-  `/password` パスワードを変更できるようになった。

## 0.2.0 - 2022-03-17
### Added
- `l22.register` ネームスペース
- `/about` にバージョン表示

## 0.1.2 - 2022-03-17
### Added
- validation. まだ sid のユニークさをチェックしていない。
- password hash
- テストユーザ

## 0.1.1-SNAPSHOT
### Added
- validation 抜きの /register


## 0.1.0 - 2022-03-16
### Changed
- l22.users テーブルを作り直した。lein test に通った。
