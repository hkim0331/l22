# L22

情報リテラシー 2022 ポータルサイトを luminus/clojure で。

## Unreleased
- l22.register のテストはどう書く？
- edit user is-admin のトグル
- l.melt の準備

## 0.2.10 - 2022-04-07
### Changed
No. これだと開発時にしか通用しない。
- version number from `project.clj`
```
(def ^:private version
  (-> "project.clj" slurp read-string (nth 2)))
```

## 0.2.9 - 2022-04-03
### Changed
- improve register.html, password.html

## 0.2.8 - 2022-03-22
### Changed
- /admin/logout を /logout に移動
- git rm -r --cached .vscode

## 0.2.7 - 2022-03-21
- `/password` エラーの表示を register, login と同じように。
  ただし、先行入力は再利用しない。
- `/admin/delete-user!`
- delete user の confirm

## 0.2.6 - 2022-03-20
### Changed
- validation は同じキーに対して複数のエントリーを設けられる。
- register に失敗した時、入力済みのフィールドに値を戻して register し直す。

## 0.2.5 - 2022-03-20
### Fixed a bug
- flash message in login

## 0.2.4 - 2022-03-19
### Added
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
