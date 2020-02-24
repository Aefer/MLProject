# MLProject
A way to launch third party Minecraft client, to instead ClientLauncher by Huang Guangtao.

# 如何使用
混淆客户端需要移动net.minecraft.client.main.Main，为其他名称，并且创建一个Main进行回调，在Tweaker中更改位置即可。
未混淆客户端除需要移动Main并回调外，需要删除net.minecraft.realms，移动net.minecraft.client.ClientBrandRetriever和net.minecraft.server.MinecraftServer。

做完以上步骤后即可使用MLauncher启动。

# 注意
本方式仅适用于有Forge修改过的LaunchWrapper，不适用其他客户端。从未针对任何服务器、任何客户端做出修改。
