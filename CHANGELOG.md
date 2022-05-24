# L22

情報リテラシー 2022 ポータルサイトを luminus/clojure で。
l22 アプリで使うアカウントを作成する。

## Unreleased
- l22.register のテストはどう書く？
- edit user is-admin のトグル
- 後から cljs を足すには？
  => KIT が leiningen 開発での問題点としている。多分、めんどくさい。
- WARNING: parse-double already refers to: #'clojure.core/parse-double in namespace: cuerdas.core, being replaced by: #'cuerdas.core/parse-double
- WARNING: parse-long already refers to: #'clojure.core/parse-long in namespace: cuerdas.core, being replaced by: #'cuerdas.core/parse-long


## 0.4.3 - 2022-05-20
- (log/info origin)...ログしない。理由は？
  log/access.log ではなく、log/l22.log の方にログされている。
- cors は本当か？自分で localhost を名乗ればいいだけ？そんな。

## 0.4.3 - 2022-05-20
- clojure.tools.logging

## 0.4.2 - 2022-05-20
- defn my-probe
- 実機 l22 でテスト

## 0.4.0 - 2022-05-20
- CORS
- リバースプロキシ下で ORIGIN はどうつく？ 本番でやってみるか。

## 0.3.1 - 2022-05-20
- [cider/cider-nrepl "0.26.0"] -> "0.28.4"
- ring.util.http-response/ok

## 0.3.0 - 2022-05-20
### Added
- l22.routes.services namespace
  swagger はただでは手に入らないか。
- get /api/users
  users が hkimura, user1, user2... を含んでしまう。
  レポートを受け取ったユーザだけリストするようにするんで、
/api/users はほとんど使わないだろう。
- get /api/user/login
  ログイン時の認証に使う予定。

## 0.2.20 - 2022-04-30
- improve home.html

## 0.2.19 - 2022-04-30
### Added
- weather & clock, 8000/tcp
### Fixed typo
- bump-version.sh

## 0.2.18 - 2022-04-26
- link to moodle, target="_blank"
- get /logout

## 0.2.17
### Added
- about に時刻表示

## 0.2.16 - 2022-04-17
### Added
- admin/users, /admin/user/:id
  delete 作った。
  update を作っていない。

## 0.2.15 - 222-04-16
### Changed
- private micro twitter -> literacy micro twitter

## 0.2.14 - 2022-04-14
### Cancel 0.2.13
- w.hkim.jp はリンクを辿らせよう。
### Added
- li に favicon

## 0.2.13 - 2022-04-14
- w.hkim.jp interface(org.clojure/data.json)
- clojure 1.11.1

## 0.2.12 - 2022-04-13
## Changed
- home.html

## 0.2.11 - 2022-04-11
throw away to improve admin page.

## 0.2.10 - 2022-04-07
- version number from `project.clj`
No. これだと開発時にしか通用しない。

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
