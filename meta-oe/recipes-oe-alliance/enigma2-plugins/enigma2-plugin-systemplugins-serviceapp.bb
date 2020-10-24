DESCRIPTION = "serviceapp service for enigma2"
AUTHOR = "Maroš Ondrášek <mx3ldev@gmail.com>"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS = "enigma2 uchardet openssl"
RDEPENDS_${PN} = "enigma2 uchardet openssl exteplayer3 ${PYTHON_PN} ${PYTHON_PN}-json"
RCONFLICTS_${PN} = "enigma2-plugin-extensions-serviceapp"
RREPLACES_${PN} = "enigma2-plugin-extensions-serviceapp"

SRCREV = "${AUTOREV}"
SRCREV_openatv = "02956ea6b05a0186667582f9f25491f18334d31b"

SRC_URI = " \
	git://github.com/carlo0815/serviceapp.git;branch=master \
	"

S = "${WORKDIR}/git"

inherit autotools gitpkgv ${PYTHON_PN}native pkgconfig gettext

CXXFLAGS_append = " -std=c++11"

PV = "0.5+git${SRCPV}"
PKGV = "0.5+git${GITPKGV}"

PR = "r0"

EXTRA_OECONF = "\
	BUILD_SYS=${BUILD_SYS} \
	HOST_SYS=${HOST_SYS} \
	STAGING_INCDIR=${STAGING_INCDIR} \
	STAGING_LIBDIR=${STAGING_LIBDIR} \
	"

PACKAGES = "${PN}"

FILES_${PN} = "\
	${libdir}/enigma2/python/Plugins/SystemPlugins/ServiceApp/__init__.py \
	${libdir}/enigma2/python/Plugins/SystemPlugins/ServiceApp/plugin.py \
	${libdir}/enigma2/python/Plugins/SystemPlugins/ServiceApp/serviceapp_client.py \
	${libdir}/enigma2/python/Plugins/SystemPlugins/ServiceApp/serviceapp.so \
	${libdir}/enigma2/python/Plugins/SystemPlugins/ServiceApp/locale/*/*/*.mo \
	"
