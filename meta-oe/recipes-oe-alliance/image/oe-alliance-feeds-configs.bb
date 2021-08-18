SUMMARY = "Configuration files for online package repositories aka feeds"

require conf/license/license-gplv2.inc

RCONFLICTS:${PN} = "distro-feed-configs"
RREPLACES:${PN} = "distro-feed-configs"
PACKAGE_ARCH = "${MACHINEBUILD}"
PR = "r0"

do_compile() {
    mkdir -p ${S}/${sysconfdir}/opkg
	for feed in all ${PACKAGE_EXTRA_ARCHS} ${MACHINE_ARCH} 3rdparty ${MACHINE}_3rdparty ; do
        echo "src/gz ${DISTRO_FEED_PREFIX}-${feed} ${DISTRO_FEED_URI}/${feed}" > ${S}/${sysconfdir}/opkg/${feed}-feed.conf
    done
}
do_compile:append:gb7252() {
    rm ${S}/${sysconfdir}/opkg/cortexa15hf-neon-vfpv4-feed.conf
    echo "src/gz ${DISTRO_FEED_PREFIX}-cortexa15hf-neon-vfpv4 ${DISTRO_FEED_URI}/cortexagb7252/cortexa15hf-neon-vfpv4" > ${S}/${sysconfdir}/opkg/cortexa15hf-neon-vfpv4-feed.conf
}

do_install () {
        install -d ${D}${sysconfdir}/opkg
        install -m 0644 ${S}/${sysconfdir}/opkg/* ${D}${sysconfdir}/opkg/
}

CONFFILES:${PN} += '${@ " ".join( [ ( "${sysconfdir}/opkg/%s-feed.conf" % feed ) for feed in "all ${PACKAGE_EXTRA_ARCHS} ${MACHINE_ARCH} 3rdparty ${MACHINE}_3rdparty  ocram".split() ] ) }'
