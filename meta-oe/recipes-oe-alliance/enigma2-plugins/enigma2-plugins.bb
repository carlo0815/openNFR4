SUMMARY = "Additional plugins for Enigma2"
MAINTAINER = "OE-Alliance team"

LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://COPYING;md5=8e37f34d0e40d32ea2bc90ee812c9131"

PACKAGES_DYNAMIC = "enigma2-plugin-(?!oea-).*"
PROVIDES = "${PN} \
    enigma2-plugin-extensions-fancontrol2 \
    "

inherit autotools-brokensep gitpkgv ${PYTHON_PN}native pkgconfig gettext ${PYTHON_PN}-dir

SRCREV = "${AUTOREV}"
PV = "${IMAGE_VERSION}+git${SRCPV}"
PKGV = "${IMAGE_VERSION}+git${GITPKGV}"
PR = "r3"

SRC_URI = "${ENIGMA2_PLUGINS_URI} file://pluginnotwanted.patch"
SRC_URI_append_openatv = " file://EPGSearch.patch"
SRC_URI_append_opennfr = " file://EPGSearch.patch"

EXTRA_OECONF = " \
    BUILD_SYS=${BUILD_SYS} \
    HOST_SYS=${HOST_SYS} \
    STAGING_INCDIR=${STAGING_INCDIR} \
    STAGING_LIBDIR=${STAGING_LIBDIR} \
    --without-debug \
    --with-po \
    --with-boxtype=${MACHINE} \
    --with-distro=${DISTRO} \
    --with-gstversion=${GST_VERSION} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'tpm', '--with-tpm' , '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'pli', '--with-pli' , '', d)} \
"

CFLAGS += "-I${STAGING_INCDIR}/tirpc"
LDFLAGS += "-ltirpc"
CXXFLAGS = " -std=c++11"

RREPLACES_enigma2-plugin-skincomponents-eventlist = "enigma2-plugin-components-eventlist"
RCONFLICTS_enigma2-plugin-skincomponents-eventlist = "enigma2-plugin-components-eventlist"

CONFFILES_${PN} += "${sysconfdir}/enigma2/movietags"
FILES_${PN} += " /usr/share/enigma2 /usr/share/fonts "
FILES_${PN}-meta = "${datadir}/meta"
FILES_enigma2-plugin-extensions-bmediacenter += " ${libdir}/enigma2/python/Components/Renderer/LizWatches.py ${libdir}/enigma2/python/Components/Converter/LizExtraNumText.py"
FILES_enigma2-plugin-skincomponents-channelselectionshorttitle += " ${libdir}/enigma2/python/Components/Converter/ChannelSelectionShortTitle.py"
FILES_enigma2-plugin-skincomponents-eventlist += " ${libdir}/enigma2/python/Components/Renderer/EventListDisplay.py ${libdir}/enigma2/python/Components/Converter/EventList.py"
FILES_enigma2-plugin-skincomponents-eventposition += " ${libdir}/enigma2/python/Components/Converter/EventPosition.py"
FILES_enigma2-plugin-skincomponents-weathercomponent += " ${libdir}/enigma2/python/Components/WeatherMSN.py ${libdir}/enigma2/python/Components/Converter/MSNWeather.py ${libdir}/enigma2/python/Components/Sources/MSNWeather.py ${libdir}/enigma2/python/Components/Renderer/MSNWeatherPixmap.py"
FILES_enigma2-plugin-skincomponents-reftopiconname += " ${libdir}/enigma2/python/Components/Converter/RefToPiconName.py"

PACKAGES += "${PN}-meta ${PN}-build-dependencies "
PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git"

DEPENDS = "enigma2 \
    ${PYTHON_PN}-pyopenssl \
    ${PYTHON_PN}-gdata \
    streamripper \
    ${PYTHON_PN}-mutagen \
    ${PYTHON_PN}-twisted \
    ${PYTHON_PN}-daap \
    ${PYTHON_PN}-google-api-client \
    ${PYTHON_PN}-httplib2 \
    ${PYTHON_PN}-youtube-dl \
    ${PYTHON_PN}-six-native \
    ${PYTHON_PN}-beautifulsoup4 \
    dvdbackup \
    libav \
    libshowiframe \
    libcddb \
    libtirpc \
    nmap \
    "

RDEPENDS_${PN} = "${PYTHON_PN}-ctypes"

python populate_packages_prepend() {
    enigma2_plugindir = bb.data.expand('${libdir}/enigma2/python/Plugins', d)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/[a-zA-Z0-9_]+.*$', 'enigma2-plugin-%s', '%s', recursive=True, match_path=True, prepend=True)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\.la$', 'enigma2-plugin-%s-dev', '%s (development)', recursive=True, match_path=True, prepend=True)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\.a$', 'enigma2-plugin-%s-staticdev', '%s (static development)', recursive=True, match_path=True, prepend=True)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/(.*/)?\.debug/.*$', 'enigma2-plugin-%s-dbg', '%s (debug)', recursive=True, match_path=True, prepend=True)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\/.*\.po$', 'enigma2-plugin-%s-po', '%s (translations)', recursive=True, match_path=True, prepend=True)

    def getControlLines(mydir, d, package):
        packagename = package[-1]
        import os
        try:
            #ac3lipsync is renamed since 20091121 to audiosync.. but rename in cvs is not possible without lost of revision history..
            #so the foldername is still ac3lipsync
            if packagename == 'audiosync':
                packagename = 'ac3lipsync'
            src = open(mydir + packagename + "/CONTROL/control").read()
        except IOError:
            return
        for line in src.split("\n"):
            full_package = package[0] + '-' + package[1] + '-' + package[2] + '-' + package[3]
            if line.startswith('Depends: '):
                # some plugins still reference twisted-* dependencies, these packages are now called ${PYTHON_PN}-twisted-*
                rdepends = []
                for depend in line[9:].split(','):
                    depend = depend.strip()
                    if depend.startswith('twisted-'):
                        rdepends.append(depend.replace('twisted-', '${PYTHON_PN}-twisted-'))
                    elif depend == 'python-re' or depend == 'python-lang' or depend == 'python-textutils':
                        pass
                    elif depend.startswith('python-'):
                        rdepends.append(depend.replace('python-', '${PYTHON_PN}-'))
                    elif depend.startswith('gst-plugins-'):
                        rdepends.append(depend.replace('gst-plugins-', 'gstreamer1.0-'))
                    elif depend.startswith('enigma2') and not depend.startswith('enigma2-'):
                        pass # Ignore silly depends on enigma2 with all kinds of misspellings
                    else:
                        rdepends.append(depend)
                rdepends = ' '.join(rdepends)
                d.setVar('RDEPENDS_' + full_package, rdepends)
            elif line.startswith('Recommends: '):
                d.setVar('RRECOMMENDS_' + full_package, line[12:])
            elif line.startswith('Description: '):
                d.setVar('DESCRIPTION_' + full_package, line[13:])
            elif line.startswith('Replaces: '):
                d.setVar('RREPLACES_' + full_package, ' '.join(line[10:].split(', ')))
            elif line.startswith('Conflicts: '):
                d.setVar('RCONFLICTS_' + full_package, ' '.join(line[11:].split(', ')))
            elif line.startswith('Maintainer: '):
                d.setVar('MAINTAINER_' + full_package, line[12:])

    mydir = d.getVar('D', True) + "/../git/"
    for package in d.getVar('PACKAGES', d, 1).split():
        getControlLines(mydir, d, package.split('-'))
}

pkg_preinst_enigma2-plugin-extensions-webinterface() {
#!/bin/sh
rm -rf /usr/lib/enigma2/python/Plugins/Extensions/WebInterface
exit 0
}

do_package_qa() {
}
