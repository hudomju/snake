package com.moped.snake.resources;

class MockFieldLoader implements FieldLoader {

	public String load() {
		return new StringBuilder( "###############\n"
								+ "# $         $ #\n"
								+ "#    #   #    #\n"
								+ "#    #   #    #\n"
								+ "#    # $ #    #\n"
								+ "#    #   #    #\n"
								+ "#    #   #    #\n"
								+ "#    # * #    #\n"
								+ "#    #   #    #\n"
								+ "#    #   #    #\n"
								+ "#    # $ #    #\n"
								+ "#    #   #    #\n"
								+ "#    #   #    #\n"
								+ "# $         $ #\n"
								+ "###############").toString();
	}

}
