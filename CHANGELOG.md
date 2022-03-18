# L22

情報リテラシー 2022 ポータルサイトを luminus/clojure で。

## Unreleased
- /password
- /admin
- /login
- l22.register のテストはどう書く？

## 0.2.2-SNAPSHOT
- パスワードを変更後の home に赤でフラッシュメッセージを出した。

## 0.2.1 - 2022-03-18
### Added
-  `/password` パスワードを変更できるようになった。

## 0.2.0 - 2022-03-17
### Added
- `l22.register` ネームスペース
- `/about` にバージョン表示

## 0.1.2 - 2022-03-17
### Added
- validation. まだsid のユニークさをチェックしていない。
- password hash
- テストユーザ

## 0.1.1-SNAPSHOT
### Added
- validation 抜きの /register


## 0.1.0 - 2022-03-16
### Changed
- l22.users テーブルを作り直した。lein test に通った。
