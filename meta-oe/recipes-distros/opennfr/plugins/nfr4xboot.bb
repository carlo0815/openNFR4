SUMMARY = "Bootmanager for NFR4XBoot."
MAINTAINER = "carlo0815"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3"

inherit gitpkgv
SRCREV = "${AUTOREV}"
PV = "2.x.+git${SRCPV}"
PKGV = "2.x.+git${GITPKGV}"
VER ="2.x"
PR = "r0"

SRC_URI = "git://github.com/carlo0815/test_boot.git"

inherit autotools-brokensep pkgconfig

S = "${WORKDIR}/git"

EXTRA_OEMAKE = "'CC=${CC}' 'RANLIB=${RANLIB}' 'AR=${AR}' 'CFLAGS=${CFLAGS} -I${S}/include -I${S}/ubi-utils/include -I=${includedir}/glib-2.0 -I=/usr/lib/glib-2.0/include -I=${includedir}/c++ -I=${includedir}/c++/mipsel-oe-linux -DWITHOUT_XATTR' 'BUILDDIR=${S}'"

do_install() {
    install -d ${D}/usr/bin
    install -m 755 ${S}/nfr4xbm ${D}/usr/lib/enigma/python/Plugins/Extensions/NFR4XBoot/bin
}
 
