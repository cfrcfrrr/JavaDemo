package performancemonitordemo;

import org.hyperic.sigar.*;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.net.InetAddress;
import java.util.Map;
import java.util.Properties;

/**
 * Java性能监控
 * 方法一：使用Runtime、System、InetAddress类获取一些基础的系统信息，如用户名、JVM的内存等，见property()方法；
 * 方法二：使用Sigar类获取详细的性能信息，如内存、cpu、网络等，见memory()至ethernet()方法；（注意要使用Sigar类需要将sigar-amd64-winnt.dll文件（不同操作系统不同）放到C:\Windows\System32中）
 * 方法三：使用JMX（Java Management Extensions，Java管理扩展），遗留问题：java.lang.management类、sun.com.management类等（http://songzi0206.iteye.com/blog/1541636）
 */
public class App 
{
    public static void main(String[] args) {
        try {
            // System信息，从jvm获取
            property();
            System.out.println("----------------------------------");
            // cpu信息
            cpu();
            System.out.println("----------------------------------");
            // 内存信息
            memory();
            System.out.println("----------------------------------");
            // 操作系统信息
            os();
            System.out.println("----------------------------------");
            // 用户信息
            who();
            System.out.println("----------------------------------");
            // 文件系统信息
            file();
            System.out.println("----------------------------------");
            // 网络信息
            net();
            System.out.println("----------------------------------");
            // 以太网信息
            ethernet();
            System.out.println("----------------------------------");
            // 使用JMX监控
            jmx();
            System.out.println("----------------------------------");

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private static void property() throws Exception {
        Map<String, String> env = System.getenv();
        System.out.println("用户名:    " + env.get("USERNAME"));
        System.out.println("计算机名:    " + env.get("COMPUTERNAME"));
        System.out.println("计算机域名:    " + env.get("USERDOMAIN"));

        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println("本地ip地址:    " + inetAddress.getHostAddress());
        System.out.println("本地主机名:    " + inetAddress.getHostName());

        Runtime runtime = Runtime.getRuntime();
        System.out.println("JVM可以使用的总内存:    " + runtime.totalMemory());
        System.out.println("JVM可以使用的剩余内存:    " + runtime.freeMemory());
        System.out.println("JVM可以使用的处理器个数:    " + runtime.availableProcessors());

        Properties properties = System.getProperties();
        System.out.println("Java的运行环境版本：    " + properties.getProperty("java.version"));
        System.out.println("Java的运行环境供应商：    " + properties.getProperty("java.vendor"));
        System.out.println("Java供应商的URL：    " + properties.getProperty("java.vendor.url"));
        System.out.println("Java的安装路径：    " + properties.getProperty("java.home"));
        System.out.println("Java的虚拟机规范版本：    " + properties.getProperty("java.vm.specification.version"));
        System.out.println("Java的虚拟机规范供应商：    " + properties.getProperty("java.vm.specification.vendor"));
        System.out.println("Java的虚拟机规范名称：    " + properties.getProperty("java.vm.specification.name"));
        System.out.println("Java的虚拟机实现版本：    " + properties.getProperty("java.vm.version"));
        System.out.println("Java的虚拟机实现供应商：    " + properties.getProperty("java.vm.vendor"));
        System.out.println("Java的虚拟机实现名称：    " + properties.getProperty("java.vm.name"));
        System.out.println("Java运行时环境规范版本：    " + properties.getProperty("java.specification.version"));
        System.out.println("Java运行时环境规范供应商：    " + properties.getProperty("java.specification.vender"));
        System.out.println("Java运行时环境规范名称：    " + properties.getProperty("java.specification.name"));
        System.out.println("Java的类格式版本号：    " + properties.getProperty("java.class.version"));
        System.out.println("Java的类路径：    " + properties.getProperty("java.class.path"));
        System.out.println("加载库时搜索的路径列表：    " + properties.getProperty("java.library.path"));
        System.out.println("默认的临时文件路径：    " + properties.getProperty("java.io.tmpdir"));
        System.out.println("一个或多个扩展目录的路径：    " + properties.getProperty("java.ext.dirs"));
        System.out.println("操作系统的名称：    " + properties.getProperty("os.name"));
        System.out.println("操作系统的构架：    " + properties.getProperty("os.arch"));
        System.out.println("操作系统的版本：    " + properties.getProperty("os.version"));
        System.out.println("文件分隔符：    " + properties.getProperty("file.separator"));
        System.out.println("路径分隔符：    " + properties.getProperty("path.separator"));
        System.out.println("行分隔符：    " + properties.getProperty("line.separator"));
        System.out.println("用户的账户名称：    " + properties.getProperty("user.name"));
        System.out.println("用户的主目录：    " + properties.getProperty("user.home"));
        System.out.println("用户的当前工作目录：    " + properties.getProperty("user.dir"));
    }

    private static void memory() throws SigarException {
        Sigar sigar = new Sigar();
        Mem mem = sigar.getMem();
        // 内存总量
        System.out.println("内存总量:    " + mem.getTotal() / 1024L + "K av");
        // 当前内存使用量
        System.out.println("当前内存使用量:    " + mem.getUsed() / 1024L + "K used");
        // 当前内存剩余量
        System.out.println("当前内存剩余量:    " + mem.getFree() / 1024L + "K free");
        Swap swap = sigar.getSwap();
        // 交换区总量
        System.out.println("交换区总量:    " + swap.getTotal() / 1024L + "K av");
        // 当前交换区使用量
        System.out.println("当前交换区使用量:    " + swap.getUsed() / 1024L + "K used");
        // 当前交换区剩余量
        System.out.println("当前交换区剩余量:    " + swap.getFree() / 1024L + "K free");
    }

    private static void cpu() throws SigarException {
        Sigar sigar = new Sigar();
        CpuInfo infos[] = sigar.getCpuInfoList();
        CpuPerc cpuList[] = null;
        cpuList = sigar.getCpuPercList();
        for (int i = 0; i < infos.length; i++) {// 不管是单块CPU还是多CPU都适用
            CpuInfo info = infos[i];
            System.out.println("第" + (i + 1) + "块CPU信息");
            System.out.println("CPU的总量MHz:    " + info.getMhz());// CPU的总量MHz
            System.out.println("CPU生产商:    " + info.getVendor());// 获得CPU的卖主，如：Intel
            System.out.println("CPU类别:    " + info.getModel());// 获得CPU的类别，如：Celeron
            System.out.println("CPU缓存数量:    " + info.getCacheSize());// 缓冲存储器数量
            printCpuPerc(cpuList[i]);
        }
    }

    private static void printCpuPerc(CpuPerc cpu) {
        System.out.println("CPU用户使用率:    " + CpuPerc.format(cpu.getUser()));// 用户使用率
        System.out.println("CPU系统使用率:    " + CpuPerc.format(cpu.getSys()));// 系统使用率
        System.out.println("CPU当前等待率:    " + CpuPerc.format(cpu.getWait()));// 当前等待率
        System.out.println("CPU当前错误率:    " + CpuPerc.format(cpu.getNice()));//
        System.out.println("CPU当前空闲率:    " + CpuPerc.format(cpu.getIdle()));// 当前空闲率
        System.out.println("CPU总的使用率:    " + CpuPerc.format(cpu.getCombined()));// 总的使用率
    }

    private static void os() {
        OperatingSystem OS = OperatingSystem.getInstance();
        // 操作系统内核类型如： 386、486、586等x86
        System.out.println("操作系统:    " + OS.getArch());
        System.out.println("操作系统CpuEndian():    " + OS.getCpuEndian());//
        System.out.println("操作系统DataModel():    " + OS.getDataModel());//
        // 系统描述
        System.out.println("操作系统的描述:    " + OS.getDescription());
        // 操作系统类型
        // System.out.println("OS.getName():    " + OS.getName());
        // System.out.println("OS.getPatchLevel():    " + OS.getPatchLevel());//
        // 操作系统的卖主
        System.out.println("操作系统的卖主:    " + OS.getVendor());
        // 卖主名称
        System.out.println("操作系统的卖主名:    " + OS.getVendorCodeName());
        // 操作系统名称
        System.out.println("操作系统名称:    " + OS.getVendorName());
        // 操作系统卖主类型
        System.out.println("操作系统卖主类型:    " + OS.getVendorVersion());
        // 操作系统的版本号
        System.out.println("操作系统的版本号:    " + OS.getVersion());
    }

    private static void who() throws SigarException {
        Sigar sigar = new Sigar();
        Who who[] = sigar.getWhoList();
        if (who != null && who.length > 0) {
            for (int i = 0; i < who.length; i++) {
                // System.out.println("当前系统进程表中的用户名" + String.valueOf(i));
                Who _who = who[i];
                System.out.println("用户控制台:    " + _who.getDevice());
                System.out.println("用户host:    " + _who.getHost());
                // System.out.println("getTime():    " + _who.getTime());
                // 当前系统进程表中的用户名
                System.out.println("当前系统进程表中的用户名:    " + _who.getUser());
            }
        }
    }

    private static void file() throws Exception {
        Sigar sigar = new Sigar();
        FileSystem fslist[] = sigar.getFileSystemList();
        for (int i = 0; i < fslist.length; i++) {
            System.out.println("分区的盘符名称" + i);
            FileSystem fs = fslist[i];
            // 分区的盘符名称
            System.out.println("盘符名称:    " + fs.getDevName());
            // 分区的盘符名称
            System.out.println("盘符路径:    " + fs.getDirName());
            System.out.println("盘符标志:    " + fs.getFlags());//
            // 文件系统类型，比如 FAT32、NTFS
            System.out.println("盘符类型:    " + fs.getSysTypeName());
            // 文件系统类型名，比如本地硬盘、光驱、网络文件系统等
            System.out.println("盘符类型名:    " + fs.getTypeName());
            // 文件系统类型
            System.out.println("盘符文件系统类型:    " + fs.getType());
            FileSystemUsage usage = null;
            usage = sigar.getFileSystemUsage(fs.getDirName());
            switch (fs.getType()) {
                case 0: // TYPE_UNKNOWN ：未知
                    break;
                case 1: // TYPE_NONE
                    break;
                case 2: // TYPE_LOCAL_DISK : 本地硬盘
                    // 文件系统总大小
                    System.out.println(fs.getDevName() + "总大小:    " + usage.getTotal() + "KB");
                    // 文件系统剩余大小
                    System.out.println(fs.getDevName() + "剩余大小:    " + usage.getFree() + "KB");
                    // 文件系统可用大小
                    System.out.println(fs.getDevName() + "可用大小:    " + usage.getAvail() + "KB");
                    // 文件系统已经使用量
                    System.out.println(fs.getDevName() + "已经使用量:    " + usage.getUsed() + "KB");
                    double usePercent = usage.getUsePercent() * 100D;
                    // 文件系统资源的利用率
                    System.out.println(fs.getDevName() + "资源的利用率:    " + usePercent + "%");
                    break;
                case 3:// TYPE_NETWORK ：网络
                    break;
                case 4:// TYPE_RAM_DISK ：闪存
                    break;
                case 5:// TYPE_CDROM ：光驱
                    break;
                case 6:// TYPE_SWAP ：页面交换
                    break;
            }
            System.out.println(fs.getDevName() + "读出：    " + usage.getDiskReads());
            System.out.println(fs.getDevName() + "写入：    " + usage.getDiskWrites());
        }
        return;
    }

    private static void net() throws Exception {
        Sigar sigar = new Sigar();
        String ifNames[] = sigar.getNetInterfaceList();
        for (int i = 0; i < ifNames.length; i++) {
            String name = ifNames[i];
            NetInterfaceConfig ifconfig = sigar.getNetInterfaceConfig(name);
            System.out.println("网络设备名:    " + name);// 网络设备名
            System.out.println("IP地址:    " + ifconfig.getAddress());// IP地址
            System.out.println("子网掩码:    " + ifconfig.getNetmask());// 子网掩码
            if ((ifconfig.getFlags() & 1L) <= 0L) {
                System.out.println("!IFF_UP...skipping getNetInterfaceStat");
                continue;
            }
            NetInterfaceStat ifstat = sigar.getNetInterfaceStat(name);
            System.out.println(name + "接收的总包裹数:" + ifstat.getRxPackets());// 接收的总包裹数
            System.out.println(name + "发送的总包裹数:" + ifstat.getTxPackets());// 发送的总包裹数
            System.out.println(name + "接收到的总字节数:" + ifstat.getRxBytes());// 接收到的总字节数
            System.out.println(name + "发送的总字节数:" + ifstat.getTxBytes());// 发送的总字节数
            System.out.println(name + "接收到的错误包数:" + ifstat.getRxErrors());// 接收到的错误包数
            System.out.println(name + "发送数据包时的错误数:" + ifstat.getTxErrors());// 发送数据包时的错误数
            System.out.println(name + "接收时丢弃的包数:" + ifstat.getRxDropped());// 接收时丢弃的包数
            System.out.println(name + "发送时丢弃的包数:" + ifstat.getTxDropped());// 发送时丢弃的包数
        }
    }

    private static void ethernet() throws SigarException {
        Sigar sigar = null;
        sigar = new Sigar();
        String[] ifaces = sigar.getNetInterfaceList();
        for (int i = 0; i < ifaces.length; i++) {
            NetInterfaceConfig cfg = sigar.getNetInterfaceConfig(ifaces[i]);
            if (NetFlags.LOOPBACK_ADDRESS.equals(cfg.getAddress()) || (cfg.getFlags() & NetFlags.IFF_LOOPBACK) != 0
                    || NetFlags.NULL_HWADDR.equals(cfg.getHwaddr())) {
                continue;
            }
            System.out.println(cfg.getName() + "IP地址:" + cfg.getAddress());// IP地址
            System.out.println(cfg.getName() + "网关广播地址:" + cfg.getBroadcast());// 网关广播地址
            System.out.println(cfg.getName() + "网卡MAC地址:" + cfg.getHwaddr());// 网卡MAC地址
            System.out.println(cfg.getName() + "子网掩码:" + cfg.getNetmask());// 子网掩码
            System.out.println(cfg.getName() + "网卡描述信息:" + cfg.getDescription());// 网卡描述信息
            System.out.println(cfg.getName() + "网卡类型" + cfg.getType());//
        }
    }

    private static void jmx() throws Exception {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage memoryUsage = memoryMXBean.getHeapMemoryUsage(); //椎内存使用情况
        long totalMemorySize = memoryUsage.getInit(); //初始的总内存
        System.out.println("初始总内存：" + memoryUsage.getInit());
        System.out.println("最大可用内存：" + memoryUsage.getMax());
        System.out.println("可使用的内存：" + memoryUsage.getUsed());
    }
}