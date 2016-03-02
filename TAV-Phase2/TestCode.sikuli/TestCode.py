wait("1456936198062.png")
if find("1456936198062.png"):
    print "Found window"
    click(Pattern("1456937372486.png").targetOffset(62,-14))
    type("5")
    type(Key.ENTER)

    if find("1456937565551.png"):
        print "The entered value (torque) was out of bounds!"

        if find(Pattern("1456937696058.png").targetOffset(62,-2)):
            
            click(Pattern("1456937696058.png").targetOffset(62,-2))
            
            type("9")
            type(Key.ENTER)

            if find("1456937753594.png"):
                print "Entered value (ultra) was out of bounds!"

                if find(Pattern("1456937798124.png").targetOffset(60,17)):
                    
                    click(Pattern("1456937798124.png").targetOffset(60,17))
                    
                    type("9")
                    type(Key.ENTER)

                    if find("1456937840419.png"):
                        print "Entered value (ir) was out of bounds!"
                        print "All values are now tested.."
                        print "Testing USB Connection..."

                        if find("1456937900601.png"):
                            click("1456937919873.png")
                            print "Check if USB is connected..."

                            if find("1456938003863.png"):
                                print "USB is now connected!"

                                print "Checking to turn off USB..."

                                if find("1456938292077.png"):

                                    click("1456938292077.png")
                                    if find("1456938326227.png"):
                                        print "USB is now disconnected..."
                                        print ""
                                        print "All tests were successfully made!"
                                        print "Shutting down!"
        
    else:
        print "Value is in range or did not find error value"
    
else:
    print "Could not find find GUI window!"