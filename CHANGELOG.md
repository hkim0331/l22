# L22

授業ポータルサイトを luminus/clojure で。

- l22.users データベースを管理する。
* web api

## Unreleased
* 受講年、受講学期もスキーマに必要か 2023-11-30
* wil-*.html を order by count desc, login では？
```sql
SQL> select login,count(login) from notes
  group by login
  order by count desc, login
  \g 2022-12-25.html
```
* struct password のバリデーション、 "this field is mandatory" 以外を表示する。
* home.html をスタティックに出す。ページルックスの一貫性のため、保留とする。2023-09-28

## 2.0.558 / 2024-04-08 14:07:23
for 2024 classes.

- bluma 1.0.0 はいろいろWarning, 0.9.4 に戻した．

- ayear(academic yearのつもり), subj をhidden fieldで追加．

```sql
l22# alter table users add column ayear int;
```

- `$ clojure -Tantq outdated :upgrade true`

| :file       | :name                               | :current | :latest |
|------------ | ----------------------------------- | -------- | --------|
| project.clj | ch.qos.logback/logback-classic      | 1.4.11   | 1.5.3   |
|             | cider/cider-nrepl                   | 0.38.1   | 0.47.1  |
|             | clojure.java-time/clojure.java-time | 1.3.0    | 1.4.2   |
|             | cprop/cprop                         | 0.1.19   | 0.1.20  |
|             | jonase/eastwood                     | 1.4.0    | 1.4.2   |
|             | markdown-clj/markdown-clj           | 1.11.7   | 1.12.1  |
|             | metosin/muuntaja                    | 0.6.8    | 0.6.10  |
|             | mount/mount                         | 0.1.17   | 0.1.18  |
|             | nrepl/nrepl                         | 1.0.0    | 1.1.1   |
|             | org.clojure/clojure                 | 1.11.1   | 1.11.2  |
|             | org.clojure/tools.cli               | 1.0.219  | 1.1.230 |
|             | org.clojure/tools.logging           | 1.2.4    | 1.3.0   |
|             | org.clojure/tools.namespace         | 1.4.4    | 1.5.0   |
|             | org.postgresql/postgresql           | 42.6.0   | 42.7.3  |
|             | org.webjars.npm/bulma               | 0.9.4    | 1.0.0   |
|             | org.webjars/webjars-locator         | 0.47     | 0.52    |
|             | ring/ring-core                      | 1.10.0   | 1.12.1  |
|             | ring/ring-devel                     | 1.10.0   | 1.12.1  |


## 1.1.31 - 2023-10-04
- libraries updated

| :file       | :name                               | :current | :latest  |
|------------ | ----------------------------------- | -------- | ---------|
| project.clj | buddy/buddy-core                    | 1.10.413 | 1.11.423 |
|             | buddy/buddy-hashers                 | 1.8.158  | 2.0.167  |
|             | buddy/buddy-sign                    | 3.4.333  | 3.5.351  |
|             | ch.qos.logback/logback-classic      | 1.4.6    | 1.4.11   |
|             | cider/cider-nrepl                   | 0.29.0   | 0.38.1   |
|             | clojure.java-time/clojure.java-time | 1.2.0    | 1.3.0    |
|             | jonase/eastwood                     | 1.3.0    | 1.4.0    |
|             | luminus-undertow/luminus-undertow   | 0.1.17   | 0.1.18   |
|             | markdown-clj/markdown-clj           | 1.11.4   | 1.11.7   |
|             | org.clojure/tools.cli               | 1.0.214  | 1.0.219  |
|             | org.postgresql/postgresql           | 42.5.1   | 42.6.0   |
|             | org.webjars.npm/material-icons      | 1.10.8   | 1.13.2   |
|             | org.webjars/webjars-locator         | 0.46     | 0.47     |
|             | ring/ring-core                      | 1.9.6    | 1.10.0   |
|             | ring/ring-defaults                  | 0.3.4    | 0.4.0    |
|             | ring/ring-devel                     | 1.9.6    | 1.10.0   |
|             | selmer/selmer                       | 1.12.58  | 1.12.59  |



## 1.0.30 - 2023-10-03
## Changed
- register/register! (log/info "register!" (dissoc params :password))
- :__anti-forgery-token 同じく。
- 過去資料のバックグラウンドを lightgrayにした。

## 1.0.29 - 2023-09-28
## Changed
- container hkim0331/l22 to hkim0331/luminus:0.1
- register: 受講クラスを先頭に移動した。

## 1.0.28 - 2023-09-10
- validation
  ログインアカウントが - 始まり、数字始まり、大文字含みにならないように。

## 1.0.27 - 2023-09-10
- deployed to p.melt
  to check permissions,

      $ sudo -u www-data stat /username/test/static

  $HOME に www-data ユーザのパーミッション問題を持ち込まないため、
  /srv/site へのシンボリックリンクが簡単。

## 1.0.26 - 2023-09-06
- 2023 後期情報処理応用準備スタート
- コンテナで動かないぞ？
  データベース l22 が見つからない。
  -> gitignore している dev-config.edn がなくて、DB のコンフィグが見つからなかった。

## 0.15.25 - 2023-06-03
- CORS
:access-control-allow-origin [#".*\.melt\.kyutech\.ac\.jp.*"]

## 0.15.24 - 2023-06-03
- 2023年受講の course を literacy とする。
- course を subj に変更。
- /api/subj/literacy returns {:users [{:login ...} {:login ...}]}

## 0.14.23
## 0.13.22 - 2023-05-31
- new link to https://rp.melt.kyutech.ac.jp/
- icon 🗒️ more colorfull one?

## 0.12.21 - 2023-04-18
### Added
- migrations/2023-04-18-update-uhour.sh: uhour を強制的に書き込み。データ投入時間から。
- migrations/2023-04-18-update-login.sh: login を強制的に書き換え。
  Mash/okym はこれ以前に削除。

## 0.12.20 - 2023-04-18
- devcontainer 設定確認した。

## 0.12.19 - 2023-04-18
- profile からログアウト、ただし、表示は `OK`
- profile に uhour が抜けていた
- profile のラベルを日本語表示

## 0.12.18 - 2023-04-17
- wil では login uhour password(hashed) しか引き出せない。
  l22 で /profile を処理する。
- deploy 時の CHANGELOG.html を作成をやめた。

## 0.12.17 - 2023-04-17
- docker hkim0331/luminus:latest での動作確認。
  start REPL -> Leiningen -> (not select any) -> OK
- アカウント確認のページは wil へ飛ばす(/profile)

## 0.12.16 - 2023-04-13
### clojure -Tantq upgrade, only same major.minors
reitit は手が滑った。

|       :file |                           :name | :current | :latest |
| ----------- | ------------------------------- | -------- | --------|
| project.clj |  ch.qos.logback/logback-classic |    1.4.5 |   1.4.6 |
|             | luminus-transit/luminus-transit |    0.1.5 |   0.1.6 |
|             |                  metosin/reitit |   0.5.18 |   0.6.0 |
|             |     org.clojure/tools.namespace |    1.3.0 |   1.4.4 |
|             |                   selmer/selmer |  1.12.55 | 1.12.58 |

### Removed
- l22.register/from-vpn?
- 不必要なテーブルをドロップした。l22.users のみ。

## 0.12.15 - 2023-04-12
### Changed
- /api/user/:login の戻りに uhour を追加。

## 0.11.14 - 2023-04-11
2023 情報リテラシースタート
### Changed
- dev-config.edn {:port 3090} l22.melt に合わせる。
  複数の clojure project を docker 以外、macos 直で動かす時、ポートのバッティングを避ける。

## 0.10.13 - 2023-02-25
- db/get-user, db/list-users でかえるコラムを login と password だけに。

## 0.10.12 - 2023-01-05
- CHANGELOG は表示しないでいいだろう。

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
- `lein uberjar` creates `/target/uberjar/l22.jar`
  not `/target/default+uberjar/l22.jar`. luminus changed?

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

## 2.0.558 /
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

## 2.0.558 /
### Added
- validation 抜きの /register


## 0.1.0 - 2022-03-16
### Changed
- l22.users テーブルを作り直した。lein test に通った。
