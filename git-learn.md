1、创建远程仓库
(1)登录github创建repository
(2)
#
2、Git 配置
(1)设置全局
git config --global user.name "John Doe"
git config --global user.email johndoe@example.com
#
3、Git常用基础命令	
(1)克隆 git clone URL <preject name>

(2)关联远程仓库 git remote origin URL

(3)第一次提交远程 git push -u origin master

(4)查看状态 git status

(5)把修改内容添加至暂存 git add -A .

(6)把暂存内容提交到本地仓库 git commit -m “message”

(7)把本地仓库提交到远程仓库 git push origin master

(8)拉取远程仓库内容至本地仓库  git fetch URL 

(9)把本地仓库内容合并到项目  git pull origin master

(10)git remote add origin你项目的地址

(11)git push -u origin master
#
4、分支管理

(1)查看分支 git branch

(2)创建分支 git branch <name>

(3)切换分支 git checkout <name>

(4)创建+切换 git checkout -b <name>

(5)创建本地和远程同步分支 git checkout -b dev origin/dev

(6)合并某分支到当前分支 git merge <name>

(7)删除分支 git branch -d <name>

5、其它命令

(1)查看远程仓库信息 git remote -v

6、H 



参数网站

1、https://www.cnblogs.com/ruofengzhishang/p/3842587.html