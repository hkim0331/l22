# L22
授業ポータルサイトを luminus/clojure で。


## Unreleased

## 0.9.11 - 2022-12-28
- バージョン番号のポリシーを major.minor.通算 に変えたんだった。
  これ、わかりにくいかも。
- L22 自体を VPN に行かせたので、これまで VPN に切り替えてから接続が必要だったサイトも、
  L22 にたどり着いたらすでに VPN、あるいは学内からのアクセス。`VPN で` の表示を止めた。
  コードセプテンバー。

## 0.9.10 - 2022-12-28
- 引っ越し、学外からは VPN で。
- favicon, apple-touch-icon のアクセスログを残さない。

## 0.8.9 - 2022-12-25
- created wil-2022-12-25.html

### Changed
- `lein uberjar` creates /target/uberjar/l22.jar
  not /target/default+uberjar/l22.jar
  luminus changed?

## 0.8.8 - 2022-10-12
- used 0.8.6 and 0.8.7, so 0.8.8.

## 0.8.6 - 2022-10-12
- welome to l22 やめて L22.
- python-20221004.zip にリンクし直し。

## 0.8.5 - 2022-10-06
- アカウント作成は終了。

## 0.8.4 - 2022-10-04
- antq outdated :upgrade true
- vscode remote container

## 0.8.3 - 2022-10-03
- alter table users add column uhour varchar(10);
- register 時に受講クラスを指定する。

## 0.8.2 - 2022-10-03

## 0.8.1 - 2022-10-03
すでに 0.8 までバージョンが上がっていたか。

## 0.8.0-SNAPSHOT
* bang access to /register and /password from 150.69.77.*.

  150.69.77 から来るのを弾くではなく、C-2G から来るのだけを受け付けるにしないと。

## 0.7.3 - 2022-09-29
- rename WILL as wil. develop started.
- added python.zip, 2022-09-29 version. newer versions are from p.melt.

## 0.7.2 - 2022-09-25
- will create `WILL`.

## 0.7.1 - 2022-09-20
- links to moodle page

## 0.7.0 - 2022-09-19
2022 後期、情報応用準備スタート。

## 0.6.0 - 2022-08-06
- 追試(8/10,1時間目、C-2A)の案内

## 0.5.2 - 2022-07-02
### Added
- db-dumps/fetch.sh

## 0.5.1 - 2022-06-18
### Added
- jh.melt.kyutech.ac.jp をリンク
### Changed
- deploy.sh は lein uberjar を含む
- home.html からアカウント作成 /register をコメントアウト
  7週経過のため、新規アカウント作成はできない。

## 0.4.5 - 2022-05-29
### Added
- /api/logins, returns ["user1" "user2" ...]
### Changed
- favicon ✍️ → 📝、㊙️ → 🌏 for windows
- :access-control-allow-origin に
  [#"http://localhost.*" #"https://rp.melt.kyutech.ac.jp.*"] では不可で、
  [#"http://localhost.*" #"https://rp.melt.kyutech.ac.jp"] なら OK なのはなぜ？
  .* だろ？any の0回以上の繰り返しのはずだが。
   もう１つ、login の際には /api/user/:login をアクセスしているのだが。


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
