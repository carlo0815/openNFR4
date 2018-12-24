SUMMARY = "Filemanager MoviePlayer Extentions"
MAINTAINER = "Coolman, Betonme & Swiss-MAD"
SECTION = "extra"
PRIORITY = "optional"
RDEPENDS_${PN} = "${@bb.utils.contains("GST_VERSION", "1.0", "gstreamer1.0-plugins-good-flv gstreamer1.0-plugins-bad-rtmp", "gst-plugins-good-flv gst-plugins-bad-rtmp", d)} python-json python-html python-requests python-mutagen librtmp1"

require conf/license/license-gplv2.inc

inherit gitpkgv pythonnative gettext
SRCREV = "${AUTOREV}"
PV = "4.0.+git${SRCPV}"
PKGV = "4.0.+git${GITPKGV}"
PR = "r13"


SRC_URI="git://github.com/betonme/e2openplugin-EnhancedMovieCenter.git \
	file://0001-Update-InfoBarSupport.py.patch"

S = "${WORKDIR}/git"

PACKAGES =+ "${PN}-src"
PACKAGES =+ "${PN}-po"
FILES_${PN} = "/etc ${libdir}"
FILES_${PN}-src = "${libdir}/enigma2/python/Plugins/Extensions/EnhancedMovieCenter/*.py"
FILES_${PN}-po = "${libdir}/enigma2/python/Plugins/Extensions/EnhancedMovieCenter/locale/*/*/*.po"

inherit autotools-brokensep

EXTRA_OECONF = "\
    --with-libsdl=no --with-boxtype=${MACHINE} --with-po \
    BUILD_SYS=${BUILD_SYS} \
    HOST_SYS=${HOST_SYS} \
    STAGING_INCDIR=${STAGING_INCDIR} \
    STAGING_LIBDIR=${STAGING_LIBDIR} \
"

CONFFILES_${PN} = "${sysconfdir}/enigma2/emc-hide.cfg ${sysconfdir}/enigma2/emc-noscan.cfg ${sysconfdir}/enigma2/emc-permsort.cfg ${sysconfdir}/enigma2/emc-topdir.cfg"


pkg_postinst_${PN}() {
#!/bin/sh
echo ""
echo ""
echo "***********************************"
echo "*     Enhanced Movie Center       *"
echo "*             V 4.0               *"
echo "*               by                *"
echo "*   Coolman, Betonme & Swiss-MAD  *"
echo "***********************************"
echo ""
echo ""
echo "Plugin successfully installed!"
echo ""
echo "You should restart enigma2 now..."
echo ""
echo ""
echo ""
exit 0
}

pkg_postrm_${PN}() {
#!/bin/sh
rm -rf ${libdir}/enigma2/python/Plugins/Extensions/EnhancedMovieCenter/
echo "Plugin removed! You should restart enigma2 now!"
exit 0
}
