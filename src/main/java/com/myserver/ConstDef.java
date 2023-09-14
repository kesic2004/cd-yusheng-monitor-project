package com.myserver;

public class ConstDef {
   public static final int HEADER_LENGTH = 3;
   public static final int QRCODE_LENGTH = 20;
   public static final int COMMAND_HEADER = 255;
   public static final int COMMAND_TO_CLIENT_OK = 204;
   public static final int COMMAND_TO_CLIENT_RETRY = 202;
   public static final int COMMAND_TO_CLIENT_START = 194;
   public static final int COMMAND_FROM_CLIENT_START = 224;
   public static final int COMMAND_FROM_CLIENT_END = 173;
   public static final int COMMAND_FROM_CLIENT_CMD1 = 164;
   public static final int DEV_STATE_NOMAL = 170;
   public static final int DEV_STATE_STOP = 85;
   public static final int DEV_STATE_TIMEOUT = 1;
   public static final int DEV_STATE_NOTTHISSTATION = 2;
}
