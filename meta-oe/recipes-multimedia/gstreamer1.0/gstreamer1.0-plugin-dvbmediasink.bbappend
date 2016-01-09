FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_sh4 = " \
    file://0001-dvbmediasink_sh4_fix.patch;patch=1 \
"

SRC_URI += " \
    file://0001-added-aactranscode-plugin_v2.patch;patch=1 \
"

SRC_URI_append_dags7335 = " \
    file://0001-update-dags-support.patch;patch=1 \ 
"
SRC_URI_append_dags7356 = " \
    file://0001-update-dags-support.patch;patch=1 \ 
"
SRC_URI_append_dags7362 = " \
    file://0001-update-dags-support.patch;patch=1 \ 
"

SRC_URI_append_dm7080 = " \
    file://0001-add-VB6-VB8-SPARK.patch;patch=1 \ 
"

SRC_URI_append_dm820 = " \
    file://0001-add-VB6-VB8-SPARK.patch;patch=1 \ 
"

SRC_URI_append_vusolo4k = " \
    file://0001-add-VB6-VB8-SPARK.patch;patch=1 \ 
"

FILES_${PN} += "${sysconfdir}/gstreamer/aactranscode"



