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

SRC_URI[md5sum] = "e34d0e28ee5b311c162060c38dc93390"
SRC_URI[sha256sum] = "e29f8b19e01dfe0f66440e6242ed2ae971ebb2bfcc2b70af12f5aa0edeb0b0eb"

RDEPENDS:${PN}  = "qtwebkit virtual/libgles2"
RDEPENDS:${PN} += "gb-v3ddriver-${MACHINE}"

S = "${WORKDIR}"

PLUGINPATH = "${libdir}/enigma2/python/Plugins/Extensions/HbbTV"

FILES:${PN} = "${bindir} ${libdir}/mozilla/plugins/libhbbtvbrowserplugin.so ${PLUGINPATH}/*.py"

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


pkg_postinst_ontarget:${PN}(){
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

INSANE_SKIP:${PN} += "already-stripped file-rdeps ldflags"
