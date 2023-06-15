import SwiftUI
import core
  
@main
struct iOSApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate
 
    var body: some Scene {
        WindowGroup {
            RicKMMortyNavigationView()
        }
    }
}
 
class AppDelegate: UIResponder, UIApplicationDelegate {
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        DIHelper.shared.doInitKoin()
        return true
    }
}
