SUMMARY = "OpenNFR base files"
LICENSE = "proprietary"

require conf/license/license-gplv2.inc

PACKAGE_ARCH := "${MACHINE_ARCH}"

SRCREV = "${AUTOREV}"

SRC_URI = ""

SRC_URI_append_mipsel = "git://github.com/carlo0815/openNFR-base.git" 
SRC_URI_append_sh4 = "git://github.com/carlo0815/openNFR-base.git;protocol=git;branch=sh4"
SRC_URI_append_formuler1 = "http://source.mynonpublic.com/formuler/formuler1-drivers-al-4.10.6-20170419.zip" 
SRC_URI_append_formuler3 = "http://source.mynonpublic.com/formuler/formuler3-drivers-al-4.10.6-20170419.zip"
SRC_URI_append_formuler4 = "http://source.mynonpublic.com/formuler/formuler4-drivers-al-4.10.6-20170419.zip"
 

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
install -d ${D}/lib/modules/${KV}/extratc
install -m 755 ${WORKDIR}/formuler1_4tc.ko ${D}/lib/modules/4.10.6/extratc/formuler1_4.ko
echo "formuler1_4" >> ${D}/${sysconfdir}/modules-load.d/_${MACHINE}.conf_tc
}
do_install_append_formuler3() {
install -d ${D}/lib/modules/${KV}/extratc
install -m 755 ${WORKDIR}/formuler3ip_dvb.ko ${D}/lib/modules/4.10.6/extratc/formuler1_4.ko
echo "formuler1_4" >> ${D}/${sysconfdir}/modules-load.d/_${MACHINE}.conf_ip 
}
do_install_append_formuler4() {
install -d ${D}/lib/modules/${KV}/extratc
install -m 755 ${WORKDIR}/formuler4ip_dvb.ko ${D}/lib/modules/4.10.6/extratc/formuler1_4.ko
echo "formuler1_4" >> ${D}/${sysconfdir}/modules-load.d/_${MACHINE}.conf_ip
}

do_populate_sysroot[noexec] = "1"
do_package_qa[noexec] = "1"
