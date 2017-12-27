SUMMARY = "OpenNFR base files"
LICENSE = "proprietary"

require conf/license/license-gplv2.inc

PACKAGE_ARCH := "${MACHINE_ARCH}"

SRCREV = "${AUTOREV}"

SRC_URI = "git://github.com/carlo0815/openNFR-base.git"

SRC_URI[formuler1.md5sum] = "43989826c0d376db159ce42c3f9dcdbb"
SRC_URI[formuler1.sha256sum] = "e41116da6709346a3314db90124bec14343c5bd8ffa56e4d16f507a16577b08b"

SRC_URI[formuler3.md5sum] = "1ccb0b941035bbfd1e44bbf046d8df69"
SRC_URI[formuler3.sha256sum] = "d67988d57a926e3431733dd12bebe65d0aa5e1244ff28b953b89df07d899af50"

SRC_URI[formuler4.md5sum] = "c29e74780f9bd1e2ca957e9cdd613437"
SRC_URI[formuler4.sha256sum] = "0f8e5505c044a44932eea1574d6f0f0d197be6e1ab81632f4023beb52caa7939"


SRC_URI_append_formuler1 += " \
http://source.mynonpublic.com/formuler/formuler1tc-drivers-al-4.10.6-20170419.zip;name=formuler1  \
" 
SRC_URI_append_formuler3 += " \
http://source.mynonpublic.com/formuler/formuler3ip-drivers-al-4.10.6-20170419.zip;name=formuler3  \
"
SRC_URI_append_formuler4 += "  \
http://source.mynonpublic.com/formuler/formuler4ip-drivers-al-4.10.6-20170419.zip;name=formuler4  \
"
 

FILES_${PN} = "/*"

INHIBIT_PACKAGE_STRIP = "1"
 
ALLOW_EMPTY_${PN} = "1"

PR = "r13"

S="${WORKDIR}/git/files"

do_install() {
}


do_install_append_mipsel() {
    install -d ${D}/media
    mkdir -p ${D}/media/hdd
    mkdir -p ${D}/media/usb
    mkdir -p ${D}/usr/lib
    cd ${D}/usr/lib
    ln -s libbz2.so.0.0.0 libbz2.so.1.0 || true
    install -d ${D}/usr/lib/enigma2/python/Components/Converter
    install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/Infopanel/data
    cp -rp ${S}/usr/lib/enigma2/python/Components/Converter/* ${D}/usr/lib/enigma2/python/Components/Converter
    cp -rp ${S}/usr/lib/enigma2/python/Plugins/Extensions/Infopanel/data/* ${D}/usr/lib/enigma2/python/Plugins/Extensions/Infopanel/data
    mv ${D}/usr/lib/enigma2/python/Components/Converter/bitratecalc.so_mips ${D}/usr/lib/enigma2/python/Components/Converter/bitratecalc.so
}

do_install_append_sh4() {
    install -d ${D}/media
    mkdir -p ${D}/media/hdd
    mkdir -p ${D}/media/usb
    mkdir -p ${D}/usr/lib
    cd ${D}/usr/lib
    ln -s libbz2.so.0.0.0 libbz2.so.1.0 || true
    install -d ${D}/usr/lib/enigma2/python/Components/Converter
    cp -rp ${S}/usr/lib/enigma2/python/Components/Converter/bitratecalc.so_sh4 ${D}/usr/lib/enigma2/python/Components/Converter/bitratecalc.so_sh4
    mv ${D}/usr/lib/enigma2/python/Components/Converter/bitratecalc.so_sh4 ${D}/usr/lib/enigma2/python/Components/Converter/bitratecalc.so
}

do_install_append_formuler1() {
install -d ${D}/${sysconfdir}/extratc
install -d ${D}/${sysconfdir}/modules-load.d
install -m 755 ${WORKDIR}/formuler1tc_dvb.ko ${D}/${sysconfdir}/extratc/formuler1_4.ko
echo "formuler1_4" >> ${D}/${sysconfdir}/modules-load.d/_${MACHINE}.conf_tc
}
do_install_append_formuler3() {
install -d ${D}/${sysconfdir}/extraip
install -d ${D}/${sysconfdir}/modules-load.d
install -m 755 ${WORKDIR}/formuler3ip_dvb.ko ${D}/${sysconfdir}/extraip/formuler3_4.ko
echo "formuler3_4" >> ${D}/${sysconfdir}/modules-load.d/_${MACHINE}.conf_ip
}
do_install_append_formuler4() {
install -d ${D}/${sysconfdir}/extraip
install -d ${D}/${sysconfdir}/modules-load.d
install -m 755 ${WORKDIR}/formuler4ip_dvb.ko ${D}/${sysconfdir}/extraip/formuler4_4.ko
echo "formuler4_4" >> ${D}/${sysconfdir}/modules-load.d/_${MACHINE}.conf_ip
}

do_populate_sysroot[noexec] = "1"
do_package_qa[noexec] = "1" 

