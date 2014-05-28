KV = "3.6.0"
SRCDATE = "20140523"

SRC_URI[md5sum] = "b12227b6e011bac88a7e47abf9102f75"
SRC_URI[sha256sum] = "cf66bcf3ae7b18cfd552469d7c0260478ac646bae3b082be326cec65e17e99d3"

SRC_URI = "http://code-ini.com/software/drivers/ini-x000-drivers-${KV}-${SRCDATE}.zip"

RREPLACES_${PN} = "venton-dvb-modules-ventonhdx"
RCONFLICTS_${PN} = "venton-dvb-modules-ventonhdx"

require ini-dvb-modules.inc
