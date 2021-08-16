DESCRIPTION = "E2 Webkit HbbTV Plugin"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"
require conf/license/license-close.inc

RDEPENDS:${PN} = "dumpait-legacy \
    ${@bb.utils.contains('TUNE_FEATURES', 'aarch64', 'lib32-webkit-hbbtv-plugin' , 'webkit-hbbtv-plugin', d)} \
"

PACKAGE_ARCH = "${MACHINEBUILD}"

inherit gitpkgv

SRCREV = "${AUTOREV}"
PKGVERSION = "2.0-r0"
PV = "${PKGVERSION}-${SRCPV}"
PKGV = "${PKGVERSION}-${GITPKGV}"
PR = "r0"

INSANE_SKIP:${PN} += "already-stripped arch"

SRC_URI = "git://github.com/oe-alliance/enigma2-plugin-extensions-hbbtv-webkit.git;protocol=https;branch=dev"

S = "${WORKDIR}/git"

do_package_qa() {
}

DESTDIR = "enigma2/python/Plugins/Extensions/HbbTV"

do_install:append() {
    install -d ${D}${libdir}/${DESTDIR}
    
    # Python Files
    cp -aRf ${S}/HbbTV/* ${D}${libdir}/${DESTDIR}
    python -O -m compileall ${D}${libdir}/${DESTDIR}
}

FILES:${PN} = "/"
