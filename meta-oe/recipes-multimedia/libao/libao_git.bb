SUMMARY = "A cross platform audio library"
LICENSE = "GPLv2"
HOMEPAGE = "http://www.xiph.org/ao/"
SECTION = "libs/multimedia"
DEPENDS = "alsa-lib"
PROVIDES = "libao-alsa libao-alsa-plugin"

LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

inherit gitpkgv

SRCREV = "${AUTOREV}"
PV = "1.2.2+git${SRCPV}"
PKGV = "1.2.2+git${GITPKGV}"
PR = "r0"

SRC_URI = "https://gitlab.xiph.org/xiph/libao"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

EXTRA_OECONF = "\
  --disable-esd \
  --disable-esdtest \
  --disable-alsa \
  --disable-arts \
  --disable-nas \
  --disable-pulse \
"

PACKAGES =+ "${PN}-alsa ${PN}-alsa-dev ${PN}-pulse ${PN}-pulse-dev ${PN}-oss ${PN}-oss-dev"

FILES:${PN}-alsa = "${libdir}/ao/plugins-2/libalsa*.so"
FILES:${PN}-alsa-dev = "${libdir}/ao/plugins-2/libalsa*.la"
FILES:${PN}-pulse = "${libdir}/ao/plugins-2/libpulse*.so"
FILES:${PN}-pulse-dev = "${libdir}/ao/plugins-2/libpulse*.la"
FILES:${PN}-oss = "${libdir}/ao/plugins-2/liboss*.so ${libdir}/ao/plugins-4/liboss*.so"
FILES:${PN}-oss-dev = "${libdir}/ao/plugins-2/liboss*.la"

FILES:${PN} += "${libdir}/ao/plugins-2/*.so ${libdir}/ckport/*"
FILES:${PN}-dev += "${libdir}/ao/plugins-2/*.la"
FILES:${PN}-dbg += "${libdir}/ao/plugins-2/.debug"
