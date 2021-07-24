### packages.yaml
这个文件用来管理不同的包管理器或者 Linux 发行版需要安装的软件包。
根据不同的包管理器和发行版我们可以将这些包大致划分为以下 4 类：
- common：适用于一些所有包管理器中包名相同或者对版本无要求的包，比如 vim 、curl、wget 这类工具。一般情况下使用这些工具我们并不关心它的版本，并且这类包的包名在所有的包管理器中都是相同的，所以这类可以划分为公共包。
- yum/apt/dnf：适用于不同的发行版使用相同的包管理器。比如 nfs 的包，在 yum 中包名为 nfs-utils 但在 apt 中为 nfs-common，这类软件包可以划分为一类。
- OS：适用于一些该 OS 独有的包，比如安装一个 Ubuntu 中有但 Debian 中没有的包（比如 debian-builder 或 ubuntu-dev-tools）。
- OS-发行版代号：这类包的版本和发行版代号绑定在一起，比如 docker-ce=5:19.03.15~3-0~debian-stretch。


https://mp.weixin.qq.com/s/Xl0qt2BGYu1g0hdtVv26Xw
