SUMMARY = "HbbTV for QT browser"
SECTION = "base"
PRIORITY = "optional"
LICENSE = "CLOSED"
require conf/license/license-close.inc
PACKAGE_ARCH := "${MACHINE_ARCH}"

SRCDATE = "20210104_r0"

PR = "r5"

inherit gitpkgv

SRC_URI = "http://dev.nachtfalke.biz/nfr/downloads/gb-hbbtv-qt-${SRCDATE}.tar.gz"

SRC_URI[md5sum] = "f95c5747351a76b28cf0137ce1707173"
SRC_URI[sha256sum] = "cb76b5275a80683d5bf8ec32b8bb0ebfa48085904c7db02cc5a0d4f24b8c8868"

RDEPENDS_${PN}  = "qtwebkit virtual/libgles2"
RDEPENDS_${PN} += "gb-v3ddriver-${MACHINE}"

S = "${WORKDIR}"

PLUGINPATH = "${libdir}/enigma2/python/Plugins/Extensions/HbbTV"

FILES_${PN} = "${bindir} ${libdir}/mozilla/plugins/libhbbtvbrowserplugin.so ${PLUGINPATH}/*.py"

do_configure_prepend () {
    sed 's/reader.doDump()/#reader.doDump()/g' -i ${S}/plugin/plugin.py
}

do_install(){
    install -d ${D}${PLUGINPATH}
    install -m 0755 ${S}/plugin/*.py ${D}${PLUGINPATH}
    install -d ${D}${bindir}
    install -m 0755 ${S}/gb_qthbbtv ${D}${bindir}
    install -m 0755 ${S}/run_hbbtv.sh ${D}${bindir}
    install -d ${D}${libdir}/mozilla/plugins
    install -m 0755 ${S}/libhbbtvbrowserplugin.so ${D}${libdir}/mozilla/plugins
}


pkg_postinst_ontarget_${PN}(){
#!/bin/sh
ln -sf /usr/share/fonts /usr/lib/fonts

# remove old PLUGINPATH
# vbcfg.py requires PLUGINROOT = "/usr/lib/enigma2/python/Plugins/Extensions/HbbTV"
if [ -d ${libdir}/enigma2/python/Plugins/Extensions/Hbbtv ]; then
  rm -rf ${libdir}/enigma2/python/Plugins/Extensions/Hbbtv
fi

exit 0
}

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

INSANE_SKIP_${PN} += "already-stripped file-rdeps ldflags"
